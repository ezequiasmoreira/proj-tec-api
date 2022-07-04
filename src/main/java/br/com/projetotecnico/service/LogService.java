package br.com.projetotecnico.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.projetotecnico.dto.LogDTO;
import br.com.projetotecnico.models.Log;
import br.com.projetotecnico.models.enums.AcaoEntity;
import br.com.projetotecnico.models.enums.TipoRetorno;
import br.com.projetotecnico.repositoty.LogRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LogService{

    private Integer identificador;

    private AcaoEntity acao;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LogRepository logRepository;

    public void salvar (Object entity){
        if(!isObjectParaLog(entity.getClass().getName())){
            return;
        }
        Method[] methods = entity.getClass().getMethods();
        String dadosEntity = getDadosEntity(entity, methods);
        logRepository.save(montarLog(dadosEntity,entity));
    }

    public Log montarLog (String dadosEntity, Object entity) {
        Log log = new Log();
        LogDTO dto = new Gson().fromJson(dadosEntity, LogDTO.class);
        log.setIdentificador(dto.getId().toString());
        log.setCampos(dadosEntity);

        log.setAcaoEntity(this.identificador == null ? AcaoEntity.CRIAR : AcaoEntity.ATUALIZAR);
        if (this.acao != null) {
            log.setAcaoEntity(this.acao);
        }

        log.setDataCadastro(new Date());
        log.setUsuario(usuarioService.getUsuarioLogado());
        log.setEntity(entity.getClass().getName());
        return  log;
    }
    public String getDadosEntity (Object entity, Method[] methods){

        String json = "{";
        for (int j = 0, m = methods.length; j < m; j++) {
            Method method = methods[j];
            if (method.getAnnotatedReturnType().toString() != "void")  {
                String valorInicialMetodo = getValorInicialMetodo(method);
                if (!valorInicialMetodo.isEmpty()) {
                    String atributo = getAtributo(valorInicialMetodo, method);
                    TipoRetorno tipoRetorno = getTipoRetorno(method);
                    String escape = (tipoRetorno.equals(TipoRetorno.OBJET)||tipoRetorno.equals(TipoRetorno.LIST_OBJET)) ? "" : "\"";
                    String campoJson = "\"" + atributo + "\":" + escape + getValorDoAtributo(method, entity) + escape;
                    json += (json== "{") ? campoJson : "," + campoJson;
                }
            }
        }
        json += "}";
        return json;
    }

    public String getValorDoAtributo(Method method, Object entity){
        try {
            TipoRetorno tipoRetorno = getTipoRetorno(method);
            if (tipoRetorno.equals(TipoRetorno.OBJET)) {
                if ((method.invoke(entity) != null ) && (isObjectParaLog(method.invoke(entity).getClass().getName()))){
                    LogDTO dto = new LogDTO();
                    dto.setId(Integer.parseInt(getValueIdFk(method.invoke(entity))));
                    return new Gson().toJson(dto);
                }
                return new Gson().toJson( method.invoke(entity));
            }

            if (tipoRetorno.equals(TipoRetorno.LIST_OBJET)){
                List listObject = (List<?>)method.invoke(entity);

                List<LogDTO> listDTO = new ArrayList<>();
                for (Object obj : listObject){
                    if(isObjectParaLog(obj.getClass().getName())) {
                        LogDTO logDTOListObject = new LogDTO();
                        logDTOListObject.setId(Integer.parseInt(getValueIdFk(obj)));
                        listDTO.add(logDTOListObject);
                    }
                }
                return new Gson().toJson(listDTO);
            }
            return method.invoke(entity).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String getValueIdFk(Object entity) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return entity.getClass().getDeclaredMethod("getId").invoke(entity).toString();
    }
    public String getAtributo(String valorInicialMetodo, Method method){
        Integer indice = valorInicialMetodo=="is"? 2 : 3;
        if ((valorInicialMetodo=="is") || (valorInicialMetodo=="get")){
            String propriedade = method.getName().substring(indice);
            return propriedade.substring(0,1).toLowerCase().concat(propriedade.substring(1));
        }
        return "";
    }

    public TipoRetorno getTipoRetorno(Method method){
        String retorno = method.getAnnotatedReturnType().toString();

        if (TipoRetorno.INTEGER.getDescricao().equals(retorno)) return TipoRetorno.INTEGER;
        if (TipoRetorno.STRING.getDescricao().equals(retorno)) return TipoRetorno.STRING;
        if (TipoRetorno.LIST.getDescricao().equals(retorno)) return TipoRetorno.LIST;
        return getTipoRetornoDinamico(retorno);
    }

    public TipoRetorno getTipoRetornoDinamico(String retorno) {
        Boolean isObjectParaLog = isObjectParaLog(getNomeObject(retorno));
        if (isList(retorno) && isObjectParaLog){
            return TipoRetorno.LIST_OBJET;
        }
        return TipoRetorno.OBJET;
    }

    public Boolean isObjectParaLog(String nomeObject){
        try {
            Object obj = Class.forName(nomeObject).getDeclaredConstructor().newInstance();
            obj.getClass().getDeclaredField("id");
            return  true;
        } catch (NoSuchFieldException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }

    public String getNomeObject(String retorno){
        Integer posicaoInicial = retorno.indexOf("<") + 1;
        Integer posicaoFinal = retorno.indexOf(">");
        return  retorno.contains("<") ? retorno.substring(posicaoInicial, posicaoFinal) : retorno;
    }

    public Boolean isList(String retorno){
        return  retorno.contains(TipoRetorno.LIST.getDescricao());
    }

    public String getValorInicialMetodo(Method method){
        String nomeDoMethod = method.getName();
        if (isMetodoReservado(nomeDoMethod)){
            return "";
        }
        if((nomeDoMethod.charAt(0) == 'i') && (nomeDoMethod.charAt(1) == 's')){
            return  "is";
        }
        if((nomeDoMethod.charAt(0) == 'g') && (nomeDoMethod.charAt(1) == 'e') && (nomeDoMethod.charAt(2) == 't')){
            return "get";
        }
        return  "";
    }
    public Boolean isMetodoReservado(String nomeDoMethod) {
        String RESERVADO = "getClass";
        return  RESERVADO.contains(nomeDoMethod);
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public void logDeleteById(Integer identificador, Object object) {
        this.identificador = identificador;
        Log log = new Log();
        log.setIdentificador(identificador.toString());
        log.setAcaoEntity(AcaoEntity.DELETE);
        log.setDataCadastro(new Date());
        log.setUsuario(usuarioService.getUsuarioLogado());
        log.setEntity(object.getClass().getName());
        logRepository.save(log);
    }

    public void logDelete(Object object) {
        this.acao = AcaoEntity.DELETE;
        salvar(object);
    }
}

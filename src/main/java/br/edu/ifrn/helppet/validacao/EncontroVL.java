package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Encontro;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author camila
 */
public class EncontroVL {

    PersistenciaGamb dao;

    public EncontroVL() {

        dao = new PersistenciaGamb();
    }

    private String validarAnimal(Encontro e) {
        if (e.getAnimal() != null) {
            if (dao.ListarAnimais().contains(e.getAnimal())) {
                return "OK";
            } else {
                return "Animal não encontrado ou não existe";
            }
        } else {
            return "Animal não encontrado ou não existe";
        }
    }

    private String validarData(Encontro e) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // setLenient serve pra indicar que a conversão não deve dar margem a erros 
        //na String ("31/06/2013" será convertido para um Date apontando para "01/07/2013", por exemplo).
        formato.setLenient(false);
        try {
            String dataFormatada = formato.format(e.getDataHorario());
            Date dataAtual = new Date();
            if (e.getDataHorario().after(dataAtual)) {
                return "OK";
            } else {
                return "Por favor, insira uma data futura";
            }
        } catch (Exception ex) {
            return "Data inválida. Por favor, insira data válida";
        }
    }

}

package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Encontro;
import br.edu.ifrn.helppet.persistencia.PersistenciaGamb;

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

}

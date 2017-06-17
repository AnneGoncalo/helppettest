package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Animal;

/**
 *
 * @author camila
 */
public class AnimalVL {

    private String validarNome(Animal a) {
        int cont = 0;
        if (a.getNomeAnimal() == null) {
            a.setNomeAnimal("Pet " + a.getIdAnimal());
            return "OK";
        } else {
            if (a.getNomeAnimal().length() >= 3 && a.getNomeAnimal().length() <= 30) {
                for (int i = 0; i < a.getNomeAnimal().length(); i++) {
                    if (Character.isLetter(a.getNomeAnimal().charAt(i))) {
                        cont++;
                    }
                }
                if (cont >= 3) {
                    return "OK";
                } else {
                    return "O nome do animal deve ter no mínimo 3 letras";
                }
            } else {
                if (a.getNomeAnimal().length() < 3) {
                    return "O nome do animal deve conter no mínimo 3 caracteres";
                } else {
                    return "O nome do animal deve conter no máximo 30 caracteres";
                }
            }
        }
    }

}

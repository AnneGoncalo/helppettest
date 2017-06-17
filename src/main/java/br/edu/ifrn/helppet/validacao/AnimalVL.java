package br.edu.ifrn.helppet.validacao;

import br.edu.ifrn.helppet.dominio.Animal;
import java.util.ArrayList;

/**
 *
 * @author camila
 */
public class AnimalVL {
    
    
    private final ArrayList<String> tipo = new ArrayList<>();
    private final ArrayList<String> especie = new ArrayList<>();
    private final ArrayList<String> idade = new ArrayList<>();
    
    public AnimalVL(){
        // Tipos válidos
        tipo.add("Adoção");        
        tipo.add("Perdido");     
        tipo.add("Resgate"); 
        // Espécies válidas
        especie.add("Gato");        
        especie.add("Cachorro");     
        especie.add("Outra");
        // Idades válidas
        idade.add("0 a 6 meses");
        idade.add("6 a 12 meses");
        idade.add("1 a 2 anos");
        idade.add("2 a 5 anos");
        idade.add("Mais de 5 anos");
    }

    
    private String validarIdade(Animal a){
        if(a.getIdade() != null){
            if(!a.getIdade().isEmpty()){
                if(tipo.contains(a.getIdade())){
                    return "OK";
                } else {
                    return "Campo inválido: "+a.getIdade();
                }
            } else {
                return "Campo vazio";
            }           
        } else {
            return "Campo nulo";
        }
    }
    
    private String validarEspecie(Animal a){
        if(a.getEspecie() != null){
            if(!a.getEspecie().isEmpty()){
                if(tipo.contains(a.getEspecie())){
                    return "OK";
                } else {
                    return "Campo inválido: "+a.getEspecie();
                }
            } else {
                return "Campo vazio";
            }           
        } else {
            return "Campo nulo";
        }
    }
    
    private String validarTipo(Animal a){
        if(a.getTipoAnimal() != null){
            if(!a.getTipoAnimal().isEmpty()){
                if(tipo.contains(a.getTipoAnimal())){
                    return "OK";
                } else {
                    return "Campo inválido: "+a.getTipoAnimal();
                }
            } else {
                return "Campo vazio";
            }           
        } else {
            return "Campo nulo";
        }
    }
    
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

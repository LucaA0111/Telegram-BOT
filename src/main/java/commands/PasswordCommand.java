package commands;


public class PasswordCommand {

    private String password = "";

    public String generatePassword(int l){
        String numbers = "1234567890";
        String lettersUP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lettersLOW = "abcdefghijklmnopqrstuvwxyz";
        String symbols = "?!-*%&$_";

        String character = numbers + lettersUP + lettersLOW + symbols;
        int characterLength = character.length();
        for (int i = 0; i < l; i++) {
            int randomIndexCharIn = (int)(Math.random()*characterLength);
            password += character.charAt(randomIndexCharIn);
        }

        return password;

    }
}

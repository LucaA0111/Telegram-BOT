package commands;

public class PinCommand {

    private String password = "";

    public String generatePin(int l){
    String numbers = "1234567890";
    int characterLength = numbers.length();
        for (int i = 0; i < l; i++) {
        int randomIndexCharInNumeri = (int) (Math.random() * characterLength);
        password += numbers.charAt(randomIndexCharInNumeri);
        }
        return password;

    }
}

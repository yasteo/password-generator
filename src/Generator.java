public class Generator {

    private final String serviceName;
    private final int serviceLength;
    private final int number;
    private final int PASSWORD_LENGTH = 16;
    private static final int unique;

    static {
        var reader = new FileReader();
        unique = reader.read();
    }

    public Generator(String serviceName, int number) {
        this.serviceName = serviceName;
        this.serviceLength = serviceName.length();
        this.number = number;
    }

    public String generate() {
        String allowedChars = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*-_+=";
        int len = allowedChars.length();
        var builder = new StringBuilder();
        for (int i = 0; i < PASSWORD_LENGTH; ++i) {
            int idx = calculateIndex(serviceName.charAt(i % serviceLength), i + 1, len);
            builder.append(allowedChars.charAt(idx));
        }
        return builder.toString();
    }

    private int calculateIndex(char c, int x, int len) {
        long idx = (long) this.number * x   * x * x + (long) this.serviceLength * x * x + (long) unique * x + (long) c;
        return (int) Math.floorMod(idx, len);
    }
}

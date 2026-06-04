public class Generator {

    private final String serviceName;
    private final int serviceLength;
    private final int number;

    public Generator(String serviceName, int number) {
        this.serviceName = serviceName;
        this.serviceLength = serviceName.length();
        this.number = number;
    }

    public String generate() {
        String allowedChars = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM!@#$%^&*-_+=";
        int len = allowedChars.length();
        var builder = new StringBuilder();
        for (int i = 0; i < 16; ++i) {
            int idx = calculateIndex(serviceName.charAt(i % serviceLength), i + 1, len);
            builder.append(allowedChars.charAt(idx));
        }
        return builder.toString();
    }

    private int calculateIndex(char c, int x, int len) {
        int idx = this.number * (x * x * x) + this.serviceLength * (x * x) + len * x + (int) c;
        return idx % len;
    }

}

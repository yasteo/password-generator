public class Generator {

    private final String serviceName;
    private final int serviceLength;
    private final int number;

    public Generator(String serviceName, int number) {
        this.serviceName = serviceName;
        this.serviceLength = serviceName.length();
        this.number = number;
    }

    public void run() {

    }

    private String generate() {
        String allowedChars = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM_+=-!@#$&*";
        int len = allowedChars.length();
        var builder = new StringBuilder();
        for (int i = 0; i < 12; ++i) {
            int idx = calculateIndex(serviceName.charAt(i % serviceLength), i + 1, len);
            builder.append(allowedChars.charAt(idx));
        }
        return builder.toString();
    }

    private int calculateIndex(char c, int x, int len) {
        int idx = this.number * (x * x * x) + (int) c * (x * x) + len * x + serviceLength;
        return idx % len;
    }

}

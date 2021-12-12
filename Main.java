import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        // Create new context for thermometer
        var context = new Context();

        // Requirement 2 Pre-Condition
        OnPowerButtonActivated(context);

        // Print current State for demonstration
        System.out.printf("[State: %s]%n", context.getCurrentState());
        
        // Requirement 2
        OnTriggerActivated(context);
    }

    public static void OnPowerButtonActivated(Context context) {
        // If power is currently off, get saved configurations and set context
        if (!context.getPowerOn()) {
            context.setConfiguration(getSavedConfiguration());
        }
        // Change status of power
        context.setPowerOn(!context.getPowerOn());

        // If power on, set State of context
        if (context.getPowerOn()) {
            context.setCurrentState(State.IDLE);
        }
    }

    private static Configuration getSavedConfiguration() {
        // Set fever limit for demonstration
        Configuration conf = new Configuration();
        conf.setFeverLimit(100.2f);
        return conf;
    }

    public static void OnTriggerActivated(Context context) {
        System.out.println("[Event: Trigger activated]");
        // Req 2.1
        context.setCurrentState(State.SCANNING);
        System.out.printf("[State: %s]%n", context.getCurrentState());
        
        // Req 2.2
        Temperature recordedTemperature = scanTemperature(context);
        
        // Req 2.7 / Post-Conditions
        DisplayResultsToScreen(context, recordedTemperature);
        context.setCurrentState(State.IDLE);
        System.out.printf("[State: %s]%n", context.getCurrentState());
    }

    private static Temperature scanTemperature(Context context) {
        // Prompt for user input to simulate temperature scan
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter temperature: ");
        String enteredTemperature = scanner.nextLine();
        Temperature temp = new Temperature();
        temp.setTemperature(Float.parseFloat(enteredTemperature));
        return temp;
    }

    public static void DisplayResultsToScreen(Context context, Temperature temperature) {
        var temp = temperature.getTemperature();
        System.out.printf("%n|==============|%n");

        // If temperature is lower than low-limit, display LOW
        // If temperature is higher than high-limit, display HIGH
        if (temp < 91.0) {
            System.out.println("| Temp: LOW    |");
        } else if (temp > 106.9) {
            System.out.println("| Temp: HIGH   |");
        } else {
            System.out.printf("| Temp: %.1f   |%n", temperature.getTemperature());
        }
        System.out.println("|              |");
        System.out.printf("| Limit: %.1f |%n", context.getConfiguration().getFeverLimit());
        System.out.printf("|==============|%n%n");
    }
}
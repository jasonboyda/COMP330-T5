import java.io.ObjectInputFilter.Config;

public class Context {
    private boolean isPowerOn;
    public boolean getPowerOn() {
        return this.isPowerOn;
    }
    public void setPowerOn(boolean value) {
        this.isPowerOn = value;
    }

    private State currentState;
    public State getCurrentState() {
        return this.currentState;
    }
    public void setCurrentState(State value) {
        this.currentState = value;
    }

    private Configuration configuration;
    public Configuration getConfiguration() {
        return this.configuration;
    }
    public void setConfiguration(Configuration value) {
        this.configuration = value;
    }
}

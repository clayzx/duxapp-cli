package {#duxapp packageName com.duxapp};
import expo.modules.ReactActivityDelegateWrapper;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;

// {#duxapp-insert import}

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "duxapp";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
   * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
   * (aka React 18) with two boolean flags.
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new ReactActivityDelegateWrapper(this, BuildConfig.IS_NEW_ARCHITECTURE_ENABLED, new MainActivityDelegate(
        this,
        getMainComponentName(),
        // If you opted-in for the New Architecture, we enable the Fabric Renderer.
        DefaultNewArchitectureEntryPoint.getFabricEnabled(), // fabricEnabled
        // If you opted-in for the New Architecture, we enable Concurrent React (i.e. React 18).
        DefaultNewArchitectureEntryPoint.getConcurrentReactEnabled() // concurrentRootEnabled
        ));
  }

  public static class MainActivityDelegate extends DefaultReactActivityDelegate {

    protected ReactActivity activity;

    public MainActivityDelegate(ReactActivity activity, String mainComponentName, Boolean fabricEnabled, Boolean concurrentReactEnabled) {
      super(activity, mainComponentName, fabricEnabled, concurrentReactEnabled);
      this.activity = activity;
    }

    @Override
    protected void loadApp(String appKey) {
      // {#duxapp-insert mainActivityDelegate.loadApp}

      super.loadApp(appKey);
    }
  }
}

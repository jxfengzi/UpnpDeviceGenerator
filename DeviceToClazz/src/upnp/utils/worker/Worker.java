
package upnp.utils.worker;

import android.content.Context;

public abstract class Worker {

    private Context context = null;

    public Worker(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public void initialize() {
    }

    public void destroy() {
    }

    public abstract void execute(Job job);
}
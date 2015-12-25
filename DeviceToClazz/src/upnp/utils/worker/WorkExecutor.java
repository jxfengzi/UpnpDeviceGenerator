
package upnp.utils.worker;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import upnp.typedef.ErrorCode;

public class WorkExecutor implements Runnable {

    private static final String TAG = WorkExecutor.class.getSimpleName();
    private static final int MAX_ACTIONS = 128;
    private Thread thread = null;
    private BlockingQueue<Job> jobsQueue = null;
    private Map<Class<?>, Worker> workers = new HashMap<Class<?>, Worker>();

    public WorkExecutor() {
        jobsQueue = new ArrayBlockingQueue<Job>(MAX_ACTIONS);
    }

    public void addWorker(Class<?> jobType, Worker worker) {
        workers.put(jobType, worker);
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            this.clear();
            this.put(new JobStop());
            thread = null;
        }
    }

    public synchronized int put(Job job) {
        jobsQueue.add(job);
        return ErrorCode.OK;
    }

    public synchronized void clear() {
        jobsQueue.clear();
    }

    @Override
    public void run() {
        Log.i(TAG, "WorkExecutor is running...");

        initializeWorkers();

        while (true) {
            Job job;

            Log.d(TAG, "taking a job...");

            try {
                job = jobsQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            if (job instanceof JobStop) {
                break;
            }

            Worker worker = this.workers.get(job.getClass());
            if (worker == null) {
                Log.d(TAG, String.format("worker not found, %s job not execute!", job.getClass().getSimpleName()));
                continue;
            }

            Log.d(TAG, worker.getClass().getSimpleName() + " executing the job: " + job.getClass().getSimpleName());

            worker.execute(job);

            Log.d(TAG, "execute job finished");
        }

        jobsQueue.clear();

        destroyWorkers();

        Log.i(TAG, "WorkExecutor is stopped");
    }

    private void initializeWorkers() {
        Log.i(TAG, "initialize Workers");

        for (Worker w : workers.values()) {
            w.initialize();
        }
    }

    private void destroyWorkers() {
        Log.i(TAG, "destroy Workers");

        for (Worker w : workers.values()) {
            w.destroy();
        }
    }
}

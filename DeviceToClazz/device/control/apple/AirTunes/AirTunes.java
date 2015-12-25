/* Automatic generated by DeviceToClazz */

package miot.api.device.xiaomi;

import android.util.Log;

import java.util.List;

import jing.api.DeviceManipulator;
import jing.api.JingManager;
import jing.api.device.AbstractService;
import jing.typedef.ReturnCode;
import jing.typedef.device.Argument;
import jing.typedef.device.Service;
import jing.typedef.device.PropertyChanged;
import jing.typedef.device.invocation.ActionInvocation;
import jing.typedef.device.invocation.ActionInvocationFactory;

public class AirTunes extends AbstractService {

    private static final String TAG = "AirTunes";

    public AirTunes(Service service) {
        super(service);
    }

    //-------------------------------------------------------
    // Action Names (10)
    //-------------------------------------------------------
    public static final String ACTION_Connect = "Connect";
    public static final String ACTION_SetVolume = "SetVolume";
    public static final String _SetVolume_ARG_volume = "volume";
    public static final String ACTION_Play = "Play";
    public static final String _Play_ARG_url = "url";
    public static final String _Play_ARG_second = "second";
    public static final String _Play_ARG_volume = "volume";
    public static final String _Play_ARG_alac = "alac";
    public static final String _Play_ARG_rsa = "rsa";
    public static final String ACTION_SetProgress = "SetProgress";
    public static final String _SetProgress_ARG_second = "second";
    public static final String ACTION_Resume = "Resume";
    public static final String ACTION_GetDuration = "GetDuration";
    public static final String _GetDuration_ARG_second = "second";
    public static final String ACTION_Pause = "Pause";
    public static final String ACTION_GetProgress = "GetProgress";
    public static final String _GetProgress_ARG_second = "second";
    public static final String ACTION_Disconnect = "Disconnect";
    public static final String ACTION_Stop = "Stop";

    //-------------------------------------------------------
    // Property Name (6)
    //-------------------------------------------------------
    public static final String PROPERTY_ALAC = "ALAC";
    public static final String PROPERTY_Url = "Url";
    public static final String PROPERTY_Volume = "Volume";
    public static final String PROPERTY_Duration = "Duration";
    public static final String PROPERTY_RSA = "RSA";
    public static final String PROPERTY_Progress = "Progress";

    //-------------------------------------------------------
    // Property value defined (0)
    //-------------------------------------------------------

    //-------------------------------------------------------
    // ActionList (10)
    //-------------------------------------------------------

    public interface Connect_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Connect(final Connect_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_Connect);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface SetVolume_CompletedHandler {
        void onSucceed(Float thevolume);
        void onFailed(int errCode, String description);
    }

    public int SetVolume(final SetVolume_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_SetVolume);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Play_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Play(String url, Long second, Float volume, Boolean alac, Boolean rsa, final Play_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_Play);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_Play_ARG_url, url, Argument.Direction.IN)) {
                ret = ReturnCode.E_ARGUMENT_INVALID;
                break;
            }
            if (!action.setArgumentValue(_Play_ARG_second, second, Argument.Direction.IN)) {
                ret = ReturnCode.E_ARGUMENT_INVALID;
                break;
            }
            if (!action.setArgumentValue(_Play_ARG_volume, volume, Argument.Direction.IN)) {
                ret = ReturnCode.E_ARGUMENT_INVALID;
                break;
            }
            if (!action.setArgumentValue(_Play_ARG_alac, alac, Argument.Direction.IN)) {
                ret = ReturnCode.E_ARGUMENT_INVALID;
                break;
            }
            if (!action.setArgumentValue(_Play_ARG_rsa, rsa, Argument.Direction.IN)) {
                ret = ReturnCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface SetProgress_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int SetProgress(Long second, final SetProgress_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_SetProgress);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }

            if (!action.setArgumentValue(_SetProgress_ARG_second, second, Argument.Direction.IN)) {
                ret = ReturnCode.E_ARGUMENT_INVALID;
                break;
            }

            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Resume_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Resume(final Resume_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_Resume);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface GetDuration_CompletedHandler {
        void onSucceed(Long thesecond);
        void onFailed(int errCode, String description);
    }

    public int GetDuration(final GetDuration_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_GetDuration);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Pause_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Pause(final Pause_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_Pause);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface GetProgress_CompletedHandler {
        void onSucceed(Long thesecond);
        void onFailed(int errCode, String description);
    }

    public int GetProgress(final GetProgress_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_GetProgress);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Disconnect_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Disconnect(final Disconnect_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_Disconnect);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    public interface Stop_CompletedHandler {
        void onSucceed();
        void onFailed(int errCode, String description);
    }

    public int Stop(final Stop_CompletedHandler handler) {
        int ret = 0;

        do {
            ActionInvocation action = ActionInvocationFactory.create(service, ACTION_Stop);;
            if (action == null) {
                ret = ReturnCode.E_ACTION_NOT_SUPPORT;
                break;
            }


            ret = JingManager.getDeviceManipulator().invoke(action, new DeviceManipulator.InvokeCompletionHandler() {

                @Override
                public void onSucceed(ActionInvocation invocation) {
                }

                @Override
                public void onFailed(final int errCode, final String description) {
                    handler.onFailed(errCode, description);
                }
            });

        } while (false);

        return ret;
    }

    //-------------------------------------------------------
    // Event
    //-------------------------------------------------------

}
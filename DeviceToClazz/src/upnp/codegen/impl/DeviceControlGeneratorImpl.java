package upnp.codegen.impl;

import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import upnp.codegen.DeviceGenerator;
import upnp.typedef.device.Action;
import upnp.typedef.device.Argument;
import upnp.typedef.device.Device;
import upnp.typedef.device.Service;
import upnp.typedef.property.AllowedValueList;
import upnp.typedef.property.AllowedValueType;
import upnp.typedef.property.Property;
import upnp.typedef.property.PropertyDefinition;
import upnp.factory.UpnpDeviceFactory;

public class DeviceControlGeneratorImpl implements DeviceGenerator {

    private static final String TAG = "DeviceControlGeneratorImpl";

    @Override
    public boolean generate(String folder, String url) throws IOException {
        boolean ret = false;
        
        do {
            Device device = UpnpDeviceFactory.create(url);
            if (device == null) {
                break;
            }

            String pkgName = String.format("upnps.api.ctrlpoint.device.%s", device.getDeviceType().getName().toLowerCase());

            generateDeviceClazz(folder, device, pkgName);
            
            for (Service s : device.getServices().values()) {
                generateServiceClazz(folder, s, pkgName);
            }
        }
        while (false);
        
        return ret;
    }
    
    private void generateDeviceClazz(String folder, Device device, String pkgName) throws IOException {
        String clazzName = device.getDeviceType().getName();
        String fileName = folder + "/" + clazzName + ".java";

        Log.d(TAG, "generateDeviceClazz: " + fileName);

        genClazzDevice(new FileOutputStream(fileName), device, pkgName);       
    }

    private void generateServiceClazz(String folder, Service service, String pkgName) throws IOException {
        String clazzName = service.getType().getName();
        String fileName = folder + "/" + clazzName + ".java";

        Log.d(TAG, "generateDeviceClazz: " + fileName);

        genClazzService(new FileOutputStream(fileName), service, pkgName);
    }

    private void genClazzDevice(OutputStream outputStream, Device device, String pkgName) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        genClazzComment(writer);
        genDeviceImport(writer, device, pkgName);

        writer.write(String.format("public class %s extends AbstractDevice {\r\n", device.getDeviceType().getName()));
        writer.write("\r\n");

        writer.write(String.format("    private static final String TAG = %s.class.getSimpleName();\r\n",
                device.getDeviceType().getName()));
        writer.write("\r\n");

        /**
         * deviceType & serviceType
         */
        writer.write("    /**\r\n");
        writer.write("     * deviceType & serviceType\r\n");
        writer.write("     */\r\n");
        writer.write(String.format("    public static final DeviceType DEVICE_TYPE = new DeviceType(\"%s\", \"%s\");\r\n",
                device.getDeviceType().getName(),
                device.getDeviceType().getVersion()));
        writer.write("\r\n");

        /**
         * serviceId
         */
        writer.write("    /**\r\n");
        writer.write("     * serviceId\r\n");
        writer.write("     */\r\n");
        for (Service s : device.getServices().values()) {
            writer.write(String.format("    public static final String ID_%s = \"%s\";\r\n", s.getType().getName(), s.getServiceId()));
        }
        writer.write("\r\n");
        
        /**
         * services
         */
        writer.write("    /**\r\n");
        writer.write("     * services\r\n");
        writer.write("     */\r\n");
        writer.write("\r\n");

        for (Service s : device.getServices().values()) {
            writer.write(String.format("    private %s _%s;\r\n", s.getType().getName(), s.getType().getName()));
        }
        writer.write("\r\n");
        
        for (Service s : device.getServices().values()) {
            writer.write(String.format("    public %s get%s() {\r\n", s.getType().getName(), s.getType().getName()));
            writer.write(String.format("        return _%s;\r\n", s.getType().getName()));
            writer.write(String.format("    }\r\n"));
        }
        writer.write("\r\n");

        /**
         * private static final Object classLock = MediaRenderer.class;
         * private static final String DEVICE_TYPE = "MediaRenderer";
         */
        writer.write(String.format("    private static final Object classLock = %s.class;\r\n", device.getDeviceType().getName()));
        writer.write(String.format("\r\n"));

        writer.write(String.format("    public static %s create(Device device) {\r\n", device.getDeviceType().getName()));
        writer.write(String.format("        Log.d(TAG, \"create\");\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("        synchronized (classLock) {\r\n"));
        writer.write(String.format("            %s thiz = new %s(device);\r\n", device.getDeviceType().getName(), device.getDeviceType().getName()));
        writer.write(String.format("\r\n"));
        writer.write(String.format("            do {\r\n"));
        writer.write(String.format("                if (! DEVICE_TYPE.equals(device.getDeviceType())) {\r\n"));
        writer.write(String.format("                    Log.d(TAG, \"deviceType invalid: \" + device.getDeviceType());\r\n"));
        writer.write(String.format("                    thiz = null;\r\n"));
        writer.write(String.format("                    break;\r\n"));
        writer.write(String.format("                }\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("                if (! thiz.initialize()) {\r\n"));
        writer.write(String.format("                    Log.d(TAG, \"initialize failed\");\r\n"));
        writer.write(String.format("                    thiz = null;\r\n"));
        writer.write(String.format("                    break;\r\n"));
        writer.write(String.format("                }\r\n"));
        writer.write(String.format("            } while (false);\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("            return thiz;\r\n"));
        writer.write(String.format("        }\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    private %s(Device device) {\r\n", device.getDeviceType().getName()));
        writer.write(String.format("        this.device = device;\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
        
        writer.write(String.format("    private boolean initialize() {\r\n"));
        writer.write(String.format("        boolean ret = false;\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("        do {\r\n"));
        for (Service s : device.getServices().values()) {
            String name = s.getType().getName();
            writer.write(String.format("            Service the%s = device.getService(ID_%s);\r\n", name, name));
            writer.write(String.format("            if (the%s == null) {\r\n", name));
            writer.write(String.format("                Log.d(TAG, \"service not found: \" + ID_%s);\r\n", s.getType().getName()));
            writer.write(String.format("                break;\r\n"));
            writer.write(String.format("            }\r\n"));
            writer.write(String.format("\r\n"));
        }

        for (Service s : device.getServices().values()) {
            String name = s.getType().getName();
            writer.write(String.format("            _%s = new %s(the%s);\r\n", name, name, name));
        }

        writer.write(String.format("\r\n"));
        writer.write(String.format("            ret = true;\r\n"));
        writer.write(String.format("        } while (false);\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("        return ret;\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
        
        writer.write(String.format("    public static final Creator<%s> CREATOR = new Creator<%s>() {\r\n",
                device.getDeviceType().getName(), device.getDeviceType().getName()));
        writer.write(String.format("\r\n"));
        writer.write(String.format("        @Override\r\n"));
        writer.write(String.format("        public %s createFromParcel(Parcel in) {\r\n", device.getDeviceType().getName()));
        writer.write(String.format("            return new %s(in);\r\n", device.getDeviceType().getName()));
        writer.write(String.format("        }\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("        @Override\r\n"));
        writer.write(String.format("        public %s[] newArray(int size) {\r\n", device.getDeviceType().getName()));
        writer.write(String.format("            return new %s[size];\r\n", device.getDeviceType().getName()));
        writer.write(String.format("         }\r\n"));
        writer.write(String.format("    };\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    private %s(Parcel in) {\r\n", device.getDeviceType().getName()));
        writer.write(String.format("        readFromParcel(in);\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    public void readFromParcel(Parcel in) {\r\n"));
        writer.write(String.format("        device = in.readParcelable(Device.class.getClassLoader());\r\n"));
        writer.write(String.format("        initialize();\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    @Override\r\n"));
        writer.write(String.format("    public int describeContents() {\r\n"));
        writer.write(String.format("        return 0;\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    @Override\r\n"));
        writer.write(String.format("    public void writeToParcel(Parcel out, int flags) {\r\n"));
        writer.write(String.format("        out.writeParcelable(device, flags);\r\n"));
        writer.write(String.format("    }\r\n"));
        
        writer.write(String.format("}\r\n"));
        writer.flush();
        writer.close();
    }
    
    private void genDeviceImport(OutputStreamWriter writer, Device device, String pkgName) throws IOException {
        StringBuilder builder = new StringBuilder(1024 * 10);

        builder.append(String.format("package %s;\r\n", pkgName));
        builder.append("\r\n");

        builder.append("import android.os.Parcel;\r\n");
        builder.append("import android.util.Log;\r\n");
        builder.append("\r\n");
        
        builder.append("import miui.upnp.typedef.device.urn.DeviceType;\r\n");
        builder.append("import miui.upnp.manager.ctrlpoint.AbstractDevice;\r\n");
        builder.append("import miui.upnp.typedef.device.Device;\r\n");
        builder.append("import miui.upnp.typedef.device.Service;\r\n");
        builder.append("\r\n");

        writer.write(builder.toString());
    }

    private void genClazzService(OutputStream outputStream, Service service, String pkgName) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        genClazzComment(writer);
        genServiceImport(writer, pkgName);

        genClazzServiceBegin(writer, service);
        genClazzServiceActionNames(writer, service);
        genClazzServicePropertyNames(writer, service);
        genClazzServicePropertyValueDefined(writer, service);
        genClazzServiceActions(writer, service);
        genClazzServiceEvent(writer, service);
        genClazzServiceEnd(writer, service);

        writer.flush();
        writer.close();
    }

    private void genClazzComment(OutputStreamWriter writer) throws IOException {
        writer.write("/* Automatic generated by DeviceToClazz */\r\n");
        writer.write("\r\n");
    }

    private void genServiceImport(OutputStreamWriter writer, String pkgName) throws IOException {
        StringBuilder builder = new StringBuilder(1024 * 10);
        
        builder.append(String.format("package %s;\r\n", pkgName));
        builder.append("\r\n");

        builder.append("import android.util.Log;\r\n");
        builder.append("\r\n");

        builder.append("import java.util.List;\r\n");
        builder.append("\r\n");

        builder.append("import miui.upnp.typedef.device.urn.ServiceType;\r\n");
        builder.append("import miui.upnp.typedef.error.UpnpError;\r\n");
        builder.append("import miui.upnp.typedef.device.Argument;\r\n");
        builder.append("import miui.upnp.typedef.device.Service;\r\n");
        builder.append("import miui.upnp.typedef.device.PropertyChanged;\r\n");
        builder.append("import miui.upnp.typedef.device.invocation.ActionInfo;\r\n");
        builder.append("import miui.upnp.typedef.device.invocation.ActionInfoCreator;\r\n");
        builder.append("import miui.upnp.typedef.exception.UpnpException;\r\n");
        builder.append("import miui.upnp.typedef.datatype.DataType;\r\n");
        builder.append("import miui.upnp.typedef.property.Property;\r\n");
        builder.append("import miui.upnp.typedef.property.PropertyDefinition;\r\n");
        builder.append("\r\n");
        
        builder.append("import miui.upnp.manager.UpnpManager;\r\n");
        builder.append("import miui.upnp.manager.ctrlpoint.AbstractService;\r\n");
        builder.append("import miui.upnp.manager.handler.MyCompletionHandler;\r\n");
        builder.append("import miui.upnp.manager.handler.MyEventListener;\r\n");
        builder.append("import miui.upnp.manager.handler.MyInvokeCompletionHandler;\r\n");

        builder.append("\r\n");

        writer.write(builder.toString());
    }

    private void genClazzServiceBegin(OutputStreamWriter writer, Service s) throws IOException {
        writer.write(String.format("public class %s extends AbstractService {\r\n", s.getType().getName()));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    private static final String TAG = \"%s\";\r\n", s.getType().getName()));
        writer.write(String.format("    private static final ServiceType SERVICE_TYPE =  new ServiceType(\"%s\", \"%s\");\r\n", s.getType().getName(), s.getType().getVersion()));
        writer.write(String.format("\r\n"));
        writer.write(String.format("    public %s(Service service) {\r\n", s.getType().getName()));
        writer.write(String.format("        super(service);\r\n"));
        writer.write(String.format("    }\r\n"));
        writer.write(String.format("\r\n"));
    }

    private void genClazzServiceActionNames(OutputStreamWriter writer, Service s) throws IOException {
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write(String.format("    // Action Names (%d)\r\n", s.getActions().size()));
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        for (Action action : s.getActions().values()) {
            writer.write(String.format("    public static final String ACTION_%s = \"%s\";\r\n", action.getName(), action.getName()));
            
            for (Argument arg : action.getArguments()) {
                writer.write(String.format("    public static final String _%s_ARG_%s = \"%s\";\r\n", action.getName(), arg.getName(), arg.getName()));
            }
        }
        writer.write("\r\n");
    }

    private void genClazzServicePropertyNames(OutputStreamWriter writer, Service s) throws IOException {
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write(String.format("    // Property Name (%d)\r\n", s.getProperties().size()));
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        for (Property p : s.getProperties()) {
            PropertyDefinition d = p.getDefinition();
            writer.write(String.format("    public static final String PROPERTY_%s = \"%s\";\r\n", d.getName(), d.getName()));
        }

        writer.write("\r\n");
    }
    
    private void genClazzServicePropertyValueDefined(OutputStreamWriter writer, Service s) throws IOException {
        int count = 0;
        for (Property p : s.getProperties()) {
            PropertyDefinition definition = p.getDefinition();
            AllowedValueList list = definition.getAllowedValueList();
            if (list != null) {
                count++;
            }
        }

        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write(String.format("    // Property value defined (%d)\r\n", count));
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write("\r\n");

        for (Property p : s.getProperties()) {
            PropertyDefinition def = p.getDefinition();
            AllowedValueList list = def.getAllowedValueList();
            if (list == null) {
                continue;
            }

            writer.write(String.format("    public enum %s {\r\n", def.getName()));
            writer.write(String.format("        UNDEFINED,\r\n"));

            int i = list.getAllowedValues().size();
            for (Object value : list.getAllowedValues()) {
                String enumValueName = getEnumValueName(value);
                writer.write(String.format("        %s", enumValueName));

                i--;
                writer.write(String.format("%s\r\n", (i > 0 ? "," : ";")));
            }
            writer.write("\r\n");

            if (def.getDataType().getJavaDataType().equals(String.class)) {
                writer.write("        private static final String CONST_UNDEFINED = \"UNDEFINED\";\r\n");
            }

            for (Object value : list.getAllowedValues()) {
                String enumValueName = getEnumValueName(value);
                if (def.getDataType().getJavaDataType().equals(String.class)) {
                    writer.write(String.format("        private static final String CONST_%s = \"%s\";\r\n", enumValueName, value));
                } else {
                    writer.write(String.format("        private static final %s CONST_%s = %s;\r\n", def.getDataType().getStringType(), enumValueName, value));
                }
            }
            writer.write("\r\n");

            // public static Mode retrieveType(String value) { ... }
            writer.write(String.format("        public static %s retrieveType(%s value) {\r\n", def.getName(), def.getDataType().getJavaDataType().getSimpleName()));
            if (def.getDataType().getJavaDataType().equals(String.class)) {
                writer.write("            if (value.equals(CONST_UNDEFINED)) {\r\n");
                writer.write("                return UNDEFINED;\r\n");
                writer.write("            }\r\n");
            }
            for (Object value : list.getAllowedValues()) {
                String enumValueName = getEnumValueName(value);
                writer.write("\r\n");

                if (def.getDataType().getJavaDataType().equals(String.class)) {
                    writer.write(String.format("            if (value.equals(CONST_%s)) {\r\n", enumValueName));
                } else {
                    writer.write(String.format("            if (value == CONST_%s) {\r\n", enumValueName));
                }

                writer.write(String.format("                return %s;\r\n", enumValueName));
                writer.write("            }\r\n");
            }
            writer.write("\r\n");
            writer.write("            return UNDEFINED;\r\n");
            writer.write("        }\r\n");
            writer.write("\r\n");

            writer.write(String.format("        public %s getValue() {\r\n", def.getDataType().getJavaDataType().getSimpleName()));

            if (def.getDataType().getJavaDataType().equals(String.class)) {
                writer.write("            String value = null;\r\n");
            } else {
                writer.write(String.format("            %s value = 0;\r\n", def.getDataType().getStringType()));
            }

            writer.write("            switch (this) {\r\n");

            for (Object value : list.getAllowedValues()) {
                String enumValueName = getEnumValueName(value);
                writer.write(String.format("                case %s:\r\n", enumValueName));
                writer.write(String.format("                    value = CONST_%s;\r\n", enumValueName));
                writer.write(String.format("                    break;\r\n"));
                writer.write("\r\n");
            }

            writer.write("                default:\r\n");
            writer.write("                    break;\r\n");
            writer.write("            }\r\n");
            writer.write("\r\n");
            writer.write("            return value;\r\n");
            writer.write("        }\r\n");
            writer.write("    }\r\n");
            writer.write("\r\n");
        }
    }

    private String getEnumValueName(Object name) {
        String n = name.toString();

        String v0 = n.replace(' ', '_');
        String v1 = v0.replace('-', '_');

        StringBuilder b = new StringBuilder(128);
        b.append("V_");
        b.append(v1);

        String v = b.toString();
        
//        Log.d(TAG, String.format("getEnumValueName(%s) = %s", n, v));

        return v;
    }

    private void genClazzServiceActions(OutputStreamWriter writer, Service s) throws IOException {
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write(String.format("    // ActionList (%d)\r\n", s.getActions().size()));
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write("\r\n");

        for (Action action : s.getActions().values()) {
            String actionName = action.getName();
            String handlerName = String.format("%s_CompletedHandler", actionName);

            /**
             * public interface GetCurrentConnectionInfoHandler {
             *     void onSuccess(String theRcsID,
             *                    String theAVTransportID,
             *                    String theProtocolInfo,
             *                    String thePeerConnectionManager,
             *                    String thePeerConnectionID,
             *                    String theDirection,
             *                    A_ARG_TYPE_ConnectionStatus theStatus);
             *     void onFailed(int code, String description);
             * }
             */
            StringBuilder builder = new StringBuilder(1024 * 4);
            builder.append(String.format("    public interface %s {\r\n", handlerName));
            builder.append(String.format("        void onSucceed("));

            int size = action.getArguments().size();
            int outSize = 0;
            int inSize = 0;

            for (int i = 0; i < size; i++) {
                Argument arg = action.getArguments().get(i);
                if (arg.getDirection() == Argument.Direction.IN) {
                    inSize++;
                } else {
                    outSize++;
                }
            }

            int i = 1;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() != Argument.Direction.OUT) {
                    continue;
                }

                PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                if (p == null) {
                    Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                    break;
                }

                if (i > 1) {
                    builder.append("                       ");
                }

                if (p.getAllowedValueType() == AllowedValueType.LIST) {
                    builder.append(String.format("%s the%s", p.getName(), arg.getName()));
                }
                else {
                    builder.append(String.format("%s the%s", p.getDataType().getJavaDataType().getSimpleName(), arg.getName()));
                }

                if (i < outSize) {
                    builder.append(",\r\n");
                }

                i++;
            }
            builder.append(String.format(");\r\n"));

            builder.append(String.format("        void onFailed(UpnpError error);\r\n"));
            builder.append(String.format("    }\r\n"));
            builder.append(String.format("\r\n"));
            
            String str = builder.toString();
            writer.write(str);
            /**
             * public void GetCurrentConnectionInfo(int theConnectionID, final GetCurrentConnectionInfoHandler handler) throws UpnpException {
             *     ActionInfo action = ActionInfoCreator.create(service, ACTION_GetCurrentConnectionInfo);
             *     if (action == null) {
             *         throw new UpnpException(UpnpError.INVALID_OPERATION, "action not found"); 
             *     }
             * 
             *     if (action.setArgumentValue(PROPERTY_CurrentConnectionIDs, theConnectionID)) {
             *         throw new UpnpException(UpnpError.INVALID_ARGUMENT);
             *     }
             * 
             *     UpnpManager.getControlPoint().invoke(invocation, new Manipulator.InvokeCompletionHandler() {
             *         @Override
             *         public void onSucceed(ActionInfo invocation) {
             *         }
             *
             *         @Override
             *         public void onFailed(int errCode, String description) {
             *              handler.onFailed(error);
             *         }
             *     });
             * }
             */
            writer.write(String.format("    public void %s(", actionName));

            size = action.getArguments().size();
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() != Argument.Direction.IN) {
                    continue;
                }

                PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                if (p == null) {
                    Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                    break;
                }

                if (p.getAllowedValueList() != null) {
                    writer.write(String.format("%s %s", p.getName(), arg.getName()));
                } else {
                    writer.write(String.format("%s %s", p.getDataType().getJavaDataType().getSimpleName(), arg.getName()));
                }
                writer.write(", ");
            }

            writer.write(String.format("final %s handler) throws UpnpException {\r\n", handlerName));
            writer.write(String.format("        ActionInfo action = ActionInfoCreator.create(service, ACTION_%s);\r\n", action.getName()));
            writer.write(String.format("        if (action == null) {\r\n"));
            writer.write(String.format("            throw new UpnpException(UpnpError.INVALID_OPERATION, \"action not found\");\r\n"));
            writer.write(String.format("        }\r\n"));
            writer.write("\r\n");

            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() != Argument.Direction.IN) {
                    continue;
                }

                PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                if (p == null) {
                    Log.d(TAG, String.format("Varialbe not found: %s", arg.getRelatedProperty()));
                    break;
                }

                writer.write(String.format("        if (!action.setArgumentValue(_%s_ARG_%s, ", action.getName(), arg.getName()));
                if (p.getAllowedValueList() == null) {
                    writer.write(String.format("%s, Argument.Direction.IN)) {\r\n", arg.getName()));
                } else {
                    writer.write(String.format("%s.getValue(), Argument.Direction.IN)) {\r\n", arg.getName()));
                }

                writer.write("            throw new UpnpException(UpnpError.INVALID_ARGUMENT);\r\n");
                writer.write("        }\r\n");
                writer.write("\r\n");
            }

            writer.write("        UpnpManager.getControlPoint().invoke(action, new MyInvokeCompletionHandler() {\r\n");
            writer.write("\r\n");
            writer.write("            @Override\r\n");
            writer.write("            public void onSucceed(ActionInfo invocation) {\r\n");

            int outCount = 0;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() != Argument.Direction.OUT) {
                    continue;
                }

                outCount++;
            }

            if (outCount == 0) {
                writer.write("                handler.onSucceed();\r\n");
            }
            else {
                writer.write("                do {\r\n");
                for (Argument arg : action.getArguments()) {
                    if (arg.getDirection() != Argument.Direction.OUT) {
                        continue;
                    }

                    PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                    if (p == null) {
                        Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                        break;
                    }

                    writer.write(String.format("                    Property p%s = invocation.getResult(_%s_ARG_%s);\r\n",
                            arg.getName(),
                            action.getName(),
                            arg.getName()));

                    writer.write(String.format("                    if (p%s == null) {\r\n", arg.getName()));
                    writer.write(String.format("                        Log.d(TAG, String.format(\"%%s not found\", _%s_ARG_%s));\r\n",
                            action.getName(),
                            arg.getName()));
                    writer.write("                        break;\r\n");
                    writer.write("                    }\r\n");
                    writer.write("\r\n");
                }

                String result[] = new String[outCount];
                i = 0;
                for (Argument arg : action.getArguments()) {
                    if (arg.getDirection() != Argument.Direction.OUT) {
                        continue;
                    }

                    PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                    if (p == null) {
                        Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                        break;
                    }

                    String name = String.format("the%s", arg.getName());
                    result[i++] = name;

                    if (p.getAllowedValueType() == AllowedValueType.LIST) {
                        writer.write(String.format("                    %s the%s = %s.retrieveType(p%s.getCurrentValue().toString());\r\n",
                                p.getName(),
                                arg.getName(),
                                p.getName(),
                                arg.getName()));
                    }
                    else {
                        writer.write(String.format("                    %s the%s = (%s) p%s.getCurrentValue();\r\n",
                                p.getDataType().getJavaDataType().getSimpleName(),
                                arg.getName(),
                                p.getDataType().getJavaDataType().getSimpleName(),
                                arg.getName()));
                    }
                }
                writer.write("\r\n");

                writer.write("                    handler.onSucceed(");
                for (i = 0; i < outCount; ++i) {
                    if (i == 0) {
                        writer.write(String.format("%s", result[i]));
                    }
                    else {
                        writer.write(String.format("                            %s", result[i]));
                    }

                    if (i != (outCount - 1)) {
                        writer.write(",\r\n");
                    }
                }
                writer.write(");\r\n");
                writer.write("                } while (false);\r\n");
            }
            writer.write("            }\r\n");

            writer.write("\r\n");
            writer.write("            @Override\r\n");
            writer.write("            public void onFailed(UpnpError error) {\r\n");
            writer.write("                handler.onFailed(error);\r\n");
            writer.write("            }\r\n");
            writer.write("        });\r\n");
            writer.write("    }\r\n");
            writer.write("\r\n");
        }
    }

    private void genClazzServiceEvent(OutputStreamWriter writer, Service s) throws IOException {
        writer.write("    //-------------------------------------------------------\r\n");
        writer.write("    // Event\r\n");
        writer.write("    //-------------------------------------------------------\r\n");
        writer.write("\r\n");

        int count = 0;
        for (Property p : s.getProperties()) {
            PropertyDefinition def = p.getDefinition();
            if (def.isSendEvents()) {
                count++;
            }
        }

        if (count == 0) {
            return;
        }

        /**
         * public interface CompletionHandler {
         *     void onSucceed();
         *     void onFailed(UpnpError error);
         * }
         */
        writer.write("    public interface CompletionHandler {\r\n");
        writer.write("        void onSucceed();\r\n");
        writer.write("        void onFailed(UpnpError error);\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");
        
        /**
         * public interface EventListener {
         *     void onSubscriptionExpired();
         *     void onSourceProtocolInfoChanged(String theSourceProtocolInfo);
         *     void onSinkProtocolInfoChanged(String theSinkProtocolInfo);
         *     void onCurrentConnectionIDsChanged(String theCurrentConnectionIDs);
         * }
         */
        writer.write("    public interface EventListener {\r\n");
        writer.write("        void onSubscriptionExpired();\r\n");
        for (Property p : s.getProperties()) {
            PropertyDefinition def = p.getDefinition();
            if (def.isSendEvents()) {
                writer.write(String.format("        void on%sChanged(", def.getName()));

                String argType = def.getDataType().getJavaDataType().getSimpleName();
                if (def.getAllowedValueList() != null) {
                    argType = def.getName();
                }

                writer.write(String.format("%s currentValue", argType));
                writer.write(");\r\n");
            }
        }
        writer.write("    }\r\n");
        writer.write("\r\n");


        writer.write("    public void subscribe(final CompletionHandler handler, final EventListener listener) throws UpnpException {\r\n");
        writer.write("        if (this.service.isSubscribed()) {\r\n");
        writer.write("            throw new UpnpException(UpnpError.SERVICE_SUBSCRIBED);\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        if (handler == null) {\r\n");
        writer.write("            throw new UpnpException(UpnpError.INVALID_ARGUMENT);\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        if (listener == null) {\r\n");
        writer.write("            throw new UpnpException(UpnpError.INVALID_ARGUMENT);\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("\r\n");
        writer.write("        UpnpManager.getControlPoint().subscribe(this.service,\r\n");
        writer.write("            new MyCompletionHandler() {\r\n");
        writer.write("\r\n");
        writer.write("                 @Override\r\n");
        writer.write("                public void onSucceed() {\r\n");
        writer.write("                    handler.onSucceed();\r\n");
        writer.write("                }\r\n");
        writer.write("\r\n");
        writer.write("                @Override\r\n");
        writer.write("                public void onFailed(UpnpError error) {\r\n");
        writer.write("                    handler.onFailed(error);\r\n");
        writer.write("                }\r\n");
        writer.write("            },\r\n");
        writer.write("            new MyEventListener() {\r\n");
        writer.write("                @Override\r\n");
        writer.write("                public void onSubscriptionExpired(String serviceId) {\r\n");
        writer.write("                    listener.onSubscriptionExpired();\r\n");
        writer.write("                }\r\n");
        writer.write("\r\n");
        writer.write("                @Override\r\n");
        writer.write("                public void onEvent(String serviceId, List<PropertyChanged> list) {\r\n");

        writer.write("                    for (PropertyChanged c : list) {\r\n");
        
        for (Property p : s.getProperties()) {
            PropertyDefinition def = p.getDefinition();
            if (def.isSendEvents()) {
                writer.write(String.format("                        if (c.getName().equals(PROPERTY_%s)) {\r\n", def.getName()));
                writer.write(String.format("                            PropertyDefinition def = service.getPropertyDefinition(PROPERTY_%s);\r\n", def.getName()));
                writer.write(String.format("                            listener.on%sChanged(", def.getName()));
                
                if (def.getAllowedValueType() == AllowedValueType.LIST) {
                    writer.write(String.format("%s.retrieveType((%s)def.getDataType().toObjectValue(c.getValue()))",
                            def.getName(),
                            def.getDataType().getJavaDataType().getSimpleName()));
                }
                else {
                    writer.write(String.format("(%s)def.getDataType().toObjectValue(c.getValue())",
                            def.getDataType().getJavaDataType().getSimpleName()));
                }
                
                writer.write(");\r\n");
                writer.write("                        }\r\n");
            }
        }

        writer.write("                    }\r\n");
        writer.write("                }\r\n");
        writer.write("            });\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");
        
        writer.write("    public void unsubscribe(final CompletionHandler handler) throws UpnpException {\r\n");
        writer.write("        if (! this.service.isSubscribed()) {\r\n");
        writer.write("            throw new UpnpException(UpnpError.SERVICE_UNSUBSCRIBED);\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        if (handler == null) {\r\n");
        writer.write("            throw new UpnpException(UpnpError.INVALID_ARGUMENT);\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        UpnpManager.getControlPoint().unsubscribe(this.service,\r\n");
        writer.write("            new MyCompletionHandler() {");
        writer.write("\r\n");
        writer.write("                @Override\r\n");
        writer.write("                public void onSucceed() {\r\n");
        writer.write("                    handler.onSucceed();\r\n");
        writer.write("                }\r\n");
        writer.write("\r\n");
        writer.write("                @Override\r\n");
        writer.write("                public void onFailed(UpnpError error) {\r\n");
        writer.write("                    handler.onFailed(error);\r\n");
        writer.write("                }\r\n");
        writer.write("            });\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");
    }

    private void genClazzServiceEnd(OutputStreamWriter writer, Service svc) throws IOException {
        writer.write("}\r\n");
    }
}
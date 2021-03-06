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

public class DeviceHostJavaGeneratorImpl implements DeviceGenerator {

    private static final String TAG = "DeviceHostGeneratorImpl";

    @Override
    public boolean generate(String folder, String url) throws IOException {
        boolean ret = false;
        
        do {
            Device device = UpnpDeviceFactory.create(url);
            if (device == null) {
                break;
            }
            
            String pkgName = String.format("upnps.api.host.device.%s", device.getDeviceType().getName().toLowerCase());

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

        writer.write(String.format("public class %s implements UpnpActionHandler {\r\n", device.getDeviceType().getName()));
        writer.write("\r\n");

        /**
         * private static final String TAG = "MediaRenderer";
         */
        writer.write(String.format("    private static final String TAG = \"%s\";\r\n", device.getDeviceType().getName()));
        writer.write("\r\n");

        /**
         * deviceType & serviceId
         */
        writer.write("    /**\r\n");
        writer.write("     * deviceType & serviceId\r\n");
        writer.write("     */\r\n");
        writer.write(String.format("    public static final DeviceType DEVICE_TYPE = new DeviceType(\"%s\", \"%s\");\r\n",
                device.getDeviceType().getName(),
                device.getDeviceType().getVersion()));
        for (Service s : device.getServices().values()) {
            writer.write(String.format("    public static final String ID_%s = \"%s\";\r\n", s.getType().getName(), s.getServiceId()));
        }
        writer.write("\r\n");

        /**
         * device & service handler;
         */
        writer.write("    /**\r\n");
        writer.write("     * device & service handler;\r\n");
        writer.write("     */\r\n");
        writer.write("    private Device _device;\r\n");
        writer.write("    private Map<String, ServiceHandler> _services = new HashMap<String, ServiceHandler>();\r\n");
        writer.write("\r\n");

        /**
         * public MediaRenderer(Context context, DeviceConfig config) throws UpnpException {
         *     _device = config.build(context, DEVICE_TYPE);
         *     _services.put(ID_AVTransport, new AVTransport(_device.getService(ID_AVTransport)));
         * }
         */
        writer.write(String.format("    public %s(Context context, DeviceConfig config) throws UpnpException {\r\n", device.getDeviceType().getName()));
        writer.write("        _device = config.build(context, DEVICE_TYPE);\r\n");
        for (Service s : device.getServices().values()) {
            String name = s.getType().getName();
            writer.write(String.format("        _services.put(ID_%s, new %s(_device));\r\n", name, name));
        }
        writer.write("    }\r\n");
        writer.write("\r\n");
        
        /**
         * public String getDeviceId() {
         *    return _device.getDeviceId();
         * }
         */
        writer.write("    public String getDeviceId() {\r\n");
        writer.write("        return _device.getDeviceId();\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * public void start(UpnpCompletionHandler handler) throws UpnpException {
         *     UpnpManager.getInstance().getHost().register(_device, handler, this);
         * }
         */
        writer.write("    public void start(UpnpCompletionHandler handler) throws UpnpException {\r\n");
        writer.write("        UpnpManager.getInstance().getHost().register(_device, handler, this);\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * public void stop(UpnpCompletionHandler handler) throws UpnpException {
         *     UpnpManager.getInstance().getHost().unregister(_device, handler);
         * }
         */
        writer.write("    public void stop(UpnpCompletionHandler handler) throws UpnpException {\r\n");
        writer.write("        UpnpManager.getInstance().getHost().unregister(_device, handler);\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * @Override
         * public UpnpError onAction(ActionInfo info) {
         *     ServiceStub handler = _services.get(info.getServiceId());
         *     if (handler == null) {
         *         Log.e(TAG, "service not found: " + info.getServiceId());
         *         return UpnpError.E_INTERNAL_ERROR;
         *     }
         *
         *     return handler.onAction(info);
         * }
         */
        writer.write("    @Override\r\n");
        writer.write("    public UpnpError onAction(ActionInfo info) {\r\n");
        writer.write("        ServiceHandler handler = _services.get(info.getServiceId());\r\n");
        writer.write("        if (handler == null) {\r\n");
        writer.write("            Log.e(TAG, \"service not found: \" + info.getServiceId());\r\n");
        writer.write("            return UpnpError.UPNP_INTERNAL_ERROR;\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        return handler.onAction(info);\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * public AVTransport getAVTransport() {
         *     return (AVTransport) _services.get(ID_AVTransport);
         * }
         * ...
         */
        for (Service s : device.getServices().values()) {
            String name = s.getType().getName();
            writer.write(String.format("    public %s get%s() {\r\n", name, name));
            writer.write(String.format("        return (%s)_services.get(ID_%s);\r\n", name, name));
            writer.write("    }\r\n");
            writer.write("\r\n");
        }

        writer.write(String.format("}\r\n"));
        writer.flush();
        writer.close();
    }

    private void genDeviceImport(OutputStreamWriter writer, Device device, String pkgName) throws IOException {
        StringBuilder builder = new StringBuilder(1024 * 10);

        builder.append(String.format("package %s;\r\n", pkgName));
        builder.append("\r\n");

        builder.append("import android.content.Context;\r\n");
        builder.append("import android.util.Log;\r\n");
        builder.append("\r\n");

        builder.append("import java.util.HashMap;\r\n");
        builder.append("import java.util.Map;\r\n");
        builder.append("\r\n");

        builder.append("import miui.upnp.typedef.device.urn.DeviceType;\r\n");
        builder.append("import miui.upnp.typedef.error.UpnpError;\r\n");
        builder.append("import miui.upnp.typedef.device.Device;\r\n");
        builder.append("import miui.upnp.typedef.device.invocation.ActionInfo;\r\n");
        builder.append("import miui.upnp.typedef.exception.UpnpException;\r\n");
        builder.append("\r\n");

        builder.append("import miui.upnp.manager.UpnpManager;\r\n");
        builder.append("import miui.upnp.manager.handler.UpnpActionHandler;\r\n");
        builder.append("import miui.upnp.manager.handler.UpnpCompletionHandler;\r\n");
        builder.append("import miui.upnp.manager.host.DeviceConfig;\r\n");
        builder.append("import miui.upnp.manager.host.ServiceHandler;\r\n");

//        for (Service s : device.getServices().values()) {
//            builder.append(String.format("import %s.%s;\r\n", pkgName, s.getType().getName()));
//        }

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
        genClazzServiceHandlerResult(writer, service);
        genClazzServiceHandler(writer, service);
        genClazzServiceMethod(writer, service);
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

        builder.append("import miui.upnp.typedef.datatype.DataType;\r\n");
        builder.append("import miui.upnp.typedef.device.Action;\r\n");
        builder.append("import miui.upnp.typedef.device.Device;\r\n");
        builder.append("import miui.upnp.typedef.device.urn.ServiceType;\r\n");
        builder.append("import miui.upnp.typedef.error.UpnpError;\r\n");
        builder.append("import miui.upnp.typedef.device.Argument;\r\n");
        builder.append("import miui.upnp.typedef.device.Service;\r\n");
        builder.append("import miui.upnp.typedef.device.invocation.ActionInfo;\r\n");
        builder.append("import miui.upnp.typedef.device.invocation.EventInfo;\r\n");
        builder.append("import miui.upnp.typedef.device.invocation.EventInfoCreator;\r\n");

        builder.append("import miui.upnp.typedef.exception.UpnpException;\r\n");
        builder.append("\r\n");

        builder.append("import miui.upnp.typedef.property.AllowedValueList;\r\n");
        builder.append("import miui.upnp.typedef.property.AllowedValueRange;\r\n");
        builder.append("import miui.upnp.typedef.property.PropertyDefinition;\r\n");
        builder.append("import miui.upnp.manager.UpnpManager;\r\n");
        builder.append("import miui.upnp.manager.host.ServiceHandler;\r\n");

        builder.append("\r\n");

        writer.write(builder.toString());
    }

    private void genClazzServiceBegin(OutputStreamWriter writer, Service s) throws IOException {
        String name = s.getType().getName();
        String ver = s.getType().getVersion();
        
        writer.write(String.format("public class %s extends ServiceHandler {\r\n", name));
        writer.write(String.format("    private static final String TAG = \"%s\";\r\n", name));
        writer.write(String.format("    private static final ServiceType SERVICE_TYPE =  new ServiceType(\"%s\", \"%s\");\r\n", name, ver));
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

    private void genClazzServiceHandlerResult(OutputStreamWriter writer, Service s) throws IOException {
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write(String.format("    // Action Result (%d)\r\n", s.getActions().size()));
        writer.write(String.format("    //-------------------------------------------------------\r\n"));
        writer.write("\r\n");

        for (Action action : s.getActions().values()) {
            String actionName = action.getName();

            int result = 0;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() == Argument.Direction.OUT) {
                    result ++;
                }
            }
            
            if (result == 0) {
                writer.write(String.format("    // %s has no Result\r\n", actionName));
                writer.write("\r\n");
                continue;
            }

            /**
             * public class GetTransportInfo_Result {
             *     public TransportState theCurrentTransportState;
             *     public TransportStatus theCurrentTransportStatus;
             *     public TransportPlaySpeed theCurrentSpeed;
             * }
             */
            writer.write(String.format("    public class %s_Result {\r\n", actionName));

            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() == Argument.Direction.OUT) {

                    PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                    if (p == null) {
                        Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                        break;
                    }

                    String dataType;
                    if (p.getAllowedValueType() == AllowedValueType.LIST) {
                        dataType = p.getName();
                    } else {
                        dataType = p.getDataType().getJavaDataType().getSimpleName();
                    }
                    writer.write(String.format("        public %s the%s;\r\n", dataType, arg.getName()));
                }
            }
            writer.write("    }\r\n");
            writer.write("\r\n");
        }

        writer.write("\r\n");
    }

    private void genClazzServiceHandler(OutputStreamWriter writer, Service s) throws IOException {
        writer.write("    //-------------------------------------------------------\r\n");
        writer.write(String.format("    // Action Handler (%s)\r\n", s.getActions().size()));
        writer.write("    //-------------------------------------------------------\r\n");
        writer.write("\r\n");

        /**
         *   public interface Handler {
         *       UpnpError onNext(Long InstanceID);
         *       UpnpError onGetTransportInfo(Long theInstanceID, GetTransportInfo_Result result);
         *       ...
         *   }
         */
        writer.write("    public interface Handler {\r\n");
        for (Action action : s.getActions().values()) {
            writer.write(String.format("        UpnpError on%s(", action.getName()));

            int argumnetCount = 0;
            int resultCount = 0;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() == Argument.Direction.IN) {
                    argumnetCount ++;
                }
                else {
                    resultCount++;
                }
            }

            int index = 0;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() == Argument.Direction.IN) {
                    PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                    if (p == null) {
                        Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                        break;
                    }

                    String dataType;
                    if (p.getAllowedValueType() == AllowedValueType.LIST) {
                        dataType = p.getName();
                    } else {
                        dataType = p.getDataType().getJavaDataType().getSimpleName();
                    }
                    writer.write(String.format("%s the%s", dataType, arg.getName()));
                    
                    index++;
                    
                    if (index < argumnetCount) {
                        writer.write(", ");                        
                    }
                }
            }

            if (resultCount > 0) {
                if (argumnetCount > 0) {
                    writer.write(", ");
                }

                writer.write(String.format("%s_Result result", action.getName()));
            }
            writer.write(");\r\n");
        }
        writer.write("    }\r\n");
        writer.write("\r\n");
        
        /**
         * private UpnpError handle_GetTransportInfo(ActionInfo info) {
         *     Long theInstanceID = (Long)info.getArgumentValue(_GetTransportInfo_ARG_InstanceID);
         *     GetTransportInfo_Result result = new GetTransportInfo_Result();
         *     
         *     UpnpError error = _handler.onGetTransportInfo(theInstanceID, result);
         *     if (! error.equals(UpnpError.OK)) {
         *         return error;
         *     }
         *          
         *     if (! info.setArgumentValue(_GetTransportInfo_ARG_CurrentTransportState, result.theCurrentTransportState, Argument.Direction.OUT)) {
         *         Log.d(TAG, "setArgumentValue: false");
         *         return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;
         *     }
         *     ...
         *     
         *     return UpnpError.OK;
         * }
         */
        for (Action action : s.getActions().values()) {
            String actionName = action.getName();
            writer.write(String.format("    private UpnpError handle_%s(ActionInfo info) {\r\n", actionName));

            int argumnetCount = 0;
            int resultCount = 0;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() == Argument.Direction.IN) {
                    argumnetCount ++;
                    String argName = arg.getName();

                    PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                    if (p.getAllowedValueType() == AllowedValueType.LIST) {
                        String dataType = p.getName();
                        writer.write(String.format("        %s the%s = %s.retrieveType((%s)info.getArgumentValue(_%s_ARG_%s));\r\n",
                                dataType, argName, dataType, p.getDataType().getJavaDataType().getSimpleName(), actionName, argName));
                    } else {
                        String dataType = p.getDataType().getJavaDataType().getSimpleName();
                        writer.write(String.format("        %s the%s = (%s)info.getArgumentValue(_%s_ARG_%s);\r\n",
                                dataType, argName, dataType, actionName, argName));
                    }
                }
                else {
                    resultCount++;
                }
            }

            if (resultCount > 0) {
                writer.write(String.format("        %s_Result result = new %s_Result();\r\n", actionName, actionName));
            }

            writer.write("\r\n");

            writer.write(String.format("        UpnpError error = _handler.on%s(", actionName));
            int index = 0;
            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() == Argument.Direction.IN) {
                    PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                    if (p == null) {
                        Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                        break;
                    }

                    String dataType;
                    if (p.getAllowedValueType() == AllowedValueType.LIST) {
                        dataType = p.getName();
                    } else {
                        dataType = p.getDataType().getJavaDataType().getSimpleName();
                    }
                    writer.write(String.format("the%s", arg.getName()));

                    index++;
                    
                    if (index < argumnetCount) {
                        writer.write(", ");                        
                    }
                }
            }
            if (resultCount > 0) {
                if (argumnetCount > 0) {
                    writer.write(", ");
                }
                writer.write("result");
            }
            writer.write(");\r\n");

            writer.write(String.format("        if (! error.equals(UpnpError.OK)) {\r\n", actionName));
            writer.write("            return error;\r\n");
            writer.write("        }\r\n");
            writer.write("\r\n");

            for (Argument arg : action.getArguments()) {
                if (arg.getDirection() != Argument.Direction.OUT) {
                    continue;
                }
                String actionArgName = String.format("_%s_ARG_%s", actionName, arg.getName());
                String resultArgName = String.format("the%s", arg.getName());

                PropertyDefinition p = s.getPropertyDefinition(arg.getRelatedProperty());
                if (p == null) {
                    Log.d(TAG, String.format("RelatedProperty not found: %s", arg.getRelatedProperty()));
                    break;
                }

                String resultValue;
                if (p.getAllowedValueType() == AllowedValueType.LIST) {
                    resultValue = String.format("result.%s.getValue()", resultArgName);
                } else {
                    resultValue = String.format("result.%s", resultArgName);
                }

                writer.write(String.format("        if (! info.setArgumentValue(%s, %s, Argument.Direction.OUT)) {\r\n", actionArgName, resultValue));
                writer.write(String.format("            Log.d(TAG, \"setArgumentValue: false\");\r\n"));
                writer.write(String.format("            return UpnpError.UPNP_ARGUMENT_VALUE_INVALID;\r\n"));
                writer.write(String.format("        }\r\n"));
                writer.write("\r\n");
            }

            writer.write("        return UpnpError.OK;\r\n");
            writer.write("    }\r\n");
            writer.write("\r\n");
        }
    }

    private void genClazzServiceMethod(OutputStreamWriter writer, Service s) throws IOException {
        /**
         * //-------------------------------------------------------
         * // Method
         * //-------------------------------------------------------
         */
        writer.write("    //-------------------------------------------------------\r\n");
        writer.write("    // Method\r\n");
        writer.write("    //-------------------------------------------------------\r\n");
        writer.write("\r\n");

        /**
         * private Service _service;
         * private Handler _handler;
         */
        writer.write("    private Service _service;\r\n");
        writer.write("    private Handler _handler;\r\n");
        writer.write("\r\n");

        /**
         * public AVTransport(Device device) {
         *     _service = new Service(SERVICE_TYPE);
         *     _service.setServiceId(toServiceId(SERVICE_TYPE));
         *     _service.setScpdUrl(toScpdUrl(device.getDeviceId(), SERVICE_TYPE));
         *     _service.setControlUrl(toCtrlUrl(device.getDeviceId(), SERVICE_TYPE));
         *     _service.setEventSubUrl(toEventUrl(device.getDeviceId(), SERVICE_TYPE));
         *
         *     Action _GetTarget = new Action(ACTION_GetTarget);
         *     _GetTarget.addArgument(new Argument(_GetTarget_ARG_RetTargetValue, Argument.Direction.OUT, PROPERTY_Target));
         *     _service.addAction(_GetTarget);
         *
         *     Action _SetTarget = new Action(ACTION_SetTarget);
         *     _SetTarget.addArgument(new Argument(_SetTarget_ARG_newTargetValue, Argument.Direction.IN, PROPERTY_Target));
         *     _service.addAction(_SetTarget);
         *
         *     Action _GetStatus = new Action(ACTION_GetStatus);
         *     _GetStatus.addArgument(new Argument(_GetStatus_ARG_ResultStatus, Argument.Direction.OUT, PROPERTY_Status));
         *     _service.addAction(_GetStatus);
         *
         *     PropertyDefinition _Target = new PropertyDefinition(PROPERTY_Target, DataType.BOOLEAN, true);
         *     _Target.setAllowedValueRange(AllowedValueRange.create(DataType.I4, 0, 100));
         *     _service.addProperty(_Target);
         *
         *     PropertyDefinition _Status = new PropertyDefinition(PROPERTY_Status, DataType.BOOLEAN, true);
         *     AllowedValueList list = new AllowedValueList(DataType.STRING);
         *     list.appendAllowedValue("ON");
         *     list.appendAllowedValue("OFF");
         *     _Status.setAllowedValueList(list);
         *     _service.addProperty(_Status);
         *
         *     device.addService(_service);
         * }
         */
        writer.write(String.format("    public %s(Device device) throws UpnpException {\r\n", s.getType().getName()));
        writer.write("        _service = new Service(SERVICE_TYPE);\r\n");
        writer.write("        _service.setServiceId(toServiceId(SERVICE_TYPE));\r\n");
        writer.write("        _service.setScpdUrl(toScpdUrl(device.getDeviceId(), SERVICE_TYPE));\r\n");
        writer.write("        _service.setControlUrl(toCtrlUrl(device.getDeviceId(), SERVICE_TYPE));\r\n");
        writer.write("        _service.setEventSubUrl(toEventUrl(device.getDeviceId(), SERVICE_TYPE));\r\n");
        writer.write("\r\n");
        
        for (Action action : s.getActions().values()) {
            String name = action.getName();
            writer.write(String.format("        Action _%s = new Action(ACTION_%s);\r\n", name, name));
            for (Argument arg : action.getArguments()) {
                writer.write(String.format("        _%s.addArgument(new Argument(_%s_ARG_%s, Argument.Direction.%s, PROPERTY_%s));\r\n",
                        name,
                        name,
                        arg.getName(),
                        arg.getDirection() == Argument.Direction.IN ? "IN" : "OUT",
                        arg.getRelatedProperty()));
            }
            writer.write(String.format("        _service.addAction(_%s);\r\n", name));
            writer.write("\r\n");
        }

        for (Property p : s.getProperties()) {
            String name = p.getDefinition().getName();
            writer.write(String.format("        PropertyDefinition _%s = new PropertyDefinition(PROPERTY_%s, DataType.%s, %s);\r\n", 
                    name,
                    name,
                    p.getDefinition().getDataType().toString(),
                    p.getDefinition().isSendEvents() ? "true" : "false"));

            switch (p.getDefinition().getAllowedValueType()) {
                case ANY:
                    break;

                case LIST:
                    writer.write(String.format("        AllowedValueList _%s_list = new AllowedValueList(DataType.%s);\r\n",
                            name,
                            p.getDefinition().getDataType().toString()));
                    
                    for (Object obj : p.getDefinition().getAllowedValueList().getAllowedValues()) {
                        writer.write(String.format("        _%s_list.appendAllowedValue(\"%s\");\r\n", name, obj));
                    }

                    writer.write(String.format("        _%s.setAllowedValueList(_%s_list);\r\n", name, name));
                    break;

                case RANGE:
                    if (p.getDefinition().getAllowedValueRange() == null) {
                       System.out.println("error: allowedValueRange == null: " + p.getDefinition().getName());
                    }

                    writer.write(String.format("        _%s.setAllowedValueRange(AllowedValueRange.create(DataType.%s, %s.valueOf(%s), %s.valueOf(%s)));\r\n",
                            name,
                            p.getDefinition().getDataType().toString(),
                            p.getDefinition().getDataType().getJavaDataType().getSimpleName(),
                            p.getDefinition().getAllowedValueRange().getMinValue(),
                            p.getDefinition().getDataType().getJavaDataType().getSimpleName(),
                            p.getDefinition().getAllowedValueRange().getMaxValue()
                            ));
                    break;
            }

            writer.write(String.format("        _service.addProperty(_%s);\r\n", name));
            writer.write("\r\n");
        }

        writer.write("        device.addService(_service);\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * public void setHandler(Handler handler) {
         *     _handler = handler;
         * }
         */
        writer.write("    public void setHandler(Handler handler) {\r\n");
        writer.write("        _handler = handler;\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * @Override
         * public UpnpError onAction(ActionInfo info) {
         *    if (_handler == null) {
         *       Log.e(TAG, "handler is null");
         *       return UpnpError.E_ACTION_NOT_IMPLEMENTED;
         *    }
         *    
         *    if (info.getAction().getName().equals(ACTION_GetVolume)) {
         *        return handle_Next(info);
         *    }
         *    ...
         * 
         *    return UpnpError.E_ACTION_NOT_IMPLEMENTED;
         * }
         */
        writer.write("    @Override\r\n");
        writer.write("    public UpnpError onAction(ActionInfo info) {\r\n");
        writer.write("        if (_handler == null) {\r\n");
        writer.write("           Log.e(TAG, \"handler is null\");\r\n");
        writer.write("           return UpnpError.UPNP_ACTION_NOT_IMPLEMENTED;\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        for (Action action : s.getActions().values()) {
            String actionName = action.getName();
            writer.write(String.format("        if (info.getAction().getName().equals(ACTION_%s)) {\r\n", actionName));
            writer.write(String.format("            return handle_%s(info);\r\n", actionName));
            writer.write("        }\r\n");
            writer.write("\r\n");        
        }
        writer.write("        return UpnpError.UPNP_ACTION_NOT_IMPLEMENTED;\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * public void sendEvents() {
         *    if (_service.isPropertyChanged()) {
         *       return;
         *    }
         *
         *    EventInfo info = EventInfoCreator.create(_service);
         *
         *    try {
         *        UpnpManager.getInstance().getHost().sendEvents(info);
         *    } catch (UpnpException e) {
         *        e.printStackTrace();
         *    }
         *
         *    _service.cleanPropertyState();
         * }
         */
        writer.write("    public void sendEvents() {\r\n");
        writer.write("        if (! _service.isPropertyChanged()) {\r\n");
        writer.write("            return;\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        EventInfo info = EventInfoCreator.create(_service);\r\n");
        writer.write("\r\n");
        writer.write("        try {\r\n");
        writer.write("            UpnpManager.getInstance().getHost().sendEvents(info);\r\n");
        writer.write("        } catch (UpnpException e) {\r\n");
        writer.write("            e.printStackTrace();\r\n");
        writer.write("        }\r\n");
        writer.write("\r\n");
        writer.write("        _service.cleanPropertyState();\r\n");
        writer.write("    }\r\n");
        writer.write("\r\n");

        /**
         * public void setLastChange(String lastChange) {
         *    _service.setPropertyValue(PROPERTY_LastChange, lastChange);
         * }
         * 
         * public String getLastChange() {
         *    return (String)_service.setPropertyValue(PROPERTY_LastChange);
         * }
         */
        for (Property p : s.getProperties()) {
            String name = p.getDefinition().getName();
            String type = null;
            if (p.getDefinition().getAllowedValueType() == AllowedValueType.LIST) {
                type = p.getDefinition().getName();
            }
            else {
                type = p.getDefinition().getDataType().getJavaDataType().getSimpleName();
            }

            writer.write(String.format("    public void set%s(%s the%s) {\r\n", name, type, name));
            if (p.getDefinition().getAllowedValueType() == AllowedValueType.LIST) {
                writer.write(String.format("         _service.setPropertyValue(PROPERTY_%s, the%s.getValue());\r\n", name, type));
            }
            else {
                writer.write(String.format("         _service.setPropertyValue(PROPERTY_%s, the%s);\r\n", name, name));
            }
            writer.write("    }\r\n");
            writer.write("\r\n");

            writer.write(String.format("    public %s get%s() {\r\n", type, name));
            if (p.getDefinition().getAllowedValueType() == AllowedValueType.LIST) {
                writer.write(String.format("         return %s.retrieveType((%s)_service.getPropertyValue(PROPERTY_%s));\r\n",
                        type,
                        p.getDefinition().getDataType().getJavaDataType().getSimpleName(),
                        name));
            }
            else {
                writer.write(String.format("         return (%s)_service.getPropertyValue(PROPERTY_%s);\r\n", type, name));
            }
            writer.write("    }\r\n");
            writer.write("\r\n");
        }
    }

    private void genClazzServiceEnd(OutputStreamWriter writer, Service svc) throws IOException {
        writer.write("}\r\n");
    }
}

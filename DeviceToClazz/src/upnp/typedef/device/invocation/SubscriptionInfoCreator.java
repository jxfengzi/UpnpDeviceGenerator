package upnp.typedef.device.invocation;

import upnp.typedef.device.Service;

public class SubscriptionInfoCreator {

    private SubscriptionInfoCreator() {
    }

    public static SubscriptionInfo create(Service service) {
        SubscriptionInfo info = new SubscriptionInfo();

        info.setHostIp(service.getDevice().getHostIp());
        info.setHostPort(service.getDevice().getHostPort());
        info.setSubscriptionId(service.getSubscriptionId());
        info.setEventSubUrl(service.getEventSubUrl());
        info.setDiscoveryType(service.getDevice().getDiscoveryType());
        info.setDeviceId(service.getDevice().getDeviceId());
        info.setServiceId(service.getServiceId());

        return info;
    }
}
package com.amazing.tengfei.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author tengfei
 * date 2020/5/4 1:10 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class EventBus {

    private static volatile EventBus defaultInstance;

    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final Map<Object, List<Class<?>>> typesBySubscriber;


    private EventBus() {
        subscriptionsByEventType = new HashMap<>();
        typesBySubscriber = new HashMap<>();
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public void register(Object object) {
        List<SubscriberMethod> subscriberMethods = new ArrayList<>();
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (subscribe != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                SubscriberMethod subscriberMethod = new SubscriberMethod(method, parameterTypes[0],
                        subscribe.threadMode(), subscribe.priority(), subscribe.sticky());
                subscriberMethods.add(subscriberMethod);
            }
        }

        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            subscriber(object, subscriberMethod);
        }

    }

    private void subscriber(Object object, SubscriberMethod subscriberMethod) {
        Class<?> eventType = subscriberMethod.eventType;
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions == null) {
            subscriptions = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(eventType, subscriptions);
        }

        Subscription subscription = new Subscription(object, subscriberMethod);
        subscriptions.add(subscription);


        List<Class<?>> eventTypes = typesBySubscriber.get(object);
        if (eventTypes == null) {
            eventTypes = new ArrayList<>();
            typesBySubscriber.put(object, eventTypes);
        }

        if (!eventTypes.contains(eventType)) {
            eventTypes.add(eventType);
        }
    }

    private void unRegister(Object object) {
        List<Class<?>> eventTypes = typesBySubscriber.get(object);
        if (eventTypes != null) {
            for (Class<?> eventType : eventTypes) {
                removeObject(eventType, object);
            }
        }

    }

    private void removeObject(Class<?> eventType, Object object) {
        List<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null) {
            int size = subscriptions.size();
            for (int i = 0; i < size; i++) {
                Subscription subscription = subscriptions.get(i);
                if (subscription.subscriber == object) {
                    subscription.active = false;
                    subscriptions.remove(i);
                    i--;
                    size--;
                }
            }
        }
    }


    public void post(Object event) {
        Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null) {
            for (Subscription subscription : subscriptions) {
                executeMethod(event, subscription);
            }
        }

    }

    private void executeMethod(Object event, Subscription subscription) {
        ThreadMode threadMode = subscription.subscriberMethod.threadMode;
        switch (threadMode) {
            case POSTING:
                invokeMethod(event, subscription);
                break;
            case MAIN:
                break;
            case ASYNC:
                break;
            case BACKGROUND:
                break;
            case MAIN_ORDERED:
                break;
            default:
                break;

        }

    }

    private void invokeMethod(Object event, Subscription subscription) {
        Method method = subscription.subscriberMethod.method;
        try {
            method.invoke(subscription.subscriber, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}

package util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;

/**
 * Определение AWARE interfaces. Осведомленные интерф. нужны если классу необходимо
 * знать в каком контексте он находится или какое у него имя в контексте. Можно
 * получ информацию от самого Spring во время инициализации бина. 
 */
public class AwareBean implements ApplicationContextAware, BeanNameAware,
        ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;
    private String name;
    private ApplicationContext ctx;

    public void init() {
        System.out.println(this.getClass().getSimpleName() + " > My name is '"
                            + name + "'");
        if (ctx != null) {
            System.out.println(this.getClass().getSimpleName() + " > My name is '"
                            + ctx.getClass().toString() + "'");
        } else {
            System.out.println(this.getClass().getSimpleName() + " > Context is not set");
        }
        if (eventPublisher != null) {
            System.out.println(this.getClass().getSimpleName() + " > My eventPublisher is '"
                             + eventPublisher.getClass().toString() + "'");
        } else {
            System.out.println(this.getClass().getSimpleName() +
                            " > EventPublisher is not set");
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher ep) {
        this.eventPublisher = ep;
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.ctx = context;
    }

}
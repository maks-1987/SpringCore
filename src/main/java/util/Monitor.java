package util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Реализация интерф ApplicationListener. Он нужен если пишется код который реагирует
 * на события контекста. Напр. создание, завершение, обновление. Реализовав его будем
 * получать события, когда они будут возникать в контексте.
 */
public class Monitor implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass().getSimpleName() + " > " +
                event.getSource().toString());
    }

}
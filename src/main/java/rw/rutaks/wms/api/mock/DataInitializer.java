package rw.rutaks.wms.api.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer {
  @Autowired Mocks mocks;

  @EventListener(ApplicationReadyEvent.class)
  public void ready() {
    mocks.createSuperAdmin();
  }
}

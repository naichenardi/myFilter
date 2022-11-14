package com.korber.myfilter.listeners;

import com.korber.myfilter.db.entities.MyFilter;
import com.korber.myfilter.db.entities.enumm.StatusFilter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class AuditingService implements AuditorAware<String> {
    @PrePersist
    private void beforePersist(MyFilter filter) {
        filter.setCreatedAt(LocalDateTime.now());
        filter.setCreatedBy(filter.getUser().getId());
        filter.addVersion();
    }

    @PreUpdate
    private void beforeUpdate(MyFilter filter) {
        filter.setUpdatedAt(LocalDateTime.now());
        filter.setUpdatedBy(filter.getUser().getId());
        filter.addVersion();
    }

    @PreRemove
    private void beforeRemove(MyFilter filter) {
        filter.setStatus(StatusFilter.DELETED);
        filter.setUpdatedAt(LocalDateTime.now());
        filter.addVersion();
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}

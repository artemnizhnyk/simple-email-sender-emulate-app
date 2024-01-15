package store.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "send_email_task")
public class SendEmailTaskEntity extends BaseEntity {

    private String message;
    private String destinationEmailAddress;
    @Builder.Default
    @Column(updatable = false, nullable = false)
    private Instant createdAt = Instant.now();
    private Instant processedAt;
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SendEmailTaskEntity)) return false;
        final SendEmailTaskEntity other = (SendEmailTaskEntity) o;
        return Objects.equals(this.getId(), other.getId());
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SendEmailTaskEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }
}

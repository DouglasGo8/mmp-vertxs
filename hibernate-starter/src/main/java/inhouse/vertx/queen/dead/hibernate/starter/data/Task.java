package inhouse.vertx.queen.dead.hibernate.starter.data;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tblTask")
public class Task {

  @Id
  @GeneratedValue
  private Integer id;
  private Integer userId;
  private String content;
  private boolean completed;
  private LocalDateTime createdAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return Objects.equals(id, task.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, content, completed, createdAt);
  }
}

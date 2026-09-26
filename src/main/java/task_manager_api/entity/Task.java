package task_manager_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task_manager_api.enums.Status;
import task_manager_api.enums.Priority;

import java.util.Date;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @PrePersist
    protected void onCreate() {
        if(this.status == null)
            status = Status.PENDING;

        if(this.priority == null)
            priority = Priority.MEDIUM;
    }

}

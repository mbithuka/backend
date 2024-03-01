package kituveki.kilumbu.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    @SuppressWarnings("unused")
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setCreatedAt(new Date());
        task.setStatus("pending");
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTaskStatus(Long id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(status);
            task.setUpdatedAt(new Date());
            return taskRepository.save(task);
        }
        return null; // Or throw an exception
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }


    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    //For an array of json

    public List<Task> createTasks(List<Task> tasks) {
        List<Task> createdTasks = new ArrayList<>();
        for (Task task : tasks) {
            Task createdTask = createTask(task);
            createdTasks.add(createdTask);
        }
        return createdTasks;
    }
}

package org.github.cursodsousa.todo.repository;

import org.github.cursodsousa.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>
{

}

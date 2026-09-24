import React from 'react';
import TaskCard from './TaskCard';

function TaskList({ tasks, onCompleteTask, onDeleteTask }) {
  if (tasks.length === 0) {
    return (
      <div style={{ textAlign: 'center', padding: '48px', color: '#718096' }}>
        <p>表示するタスクがありません</p>
      </div>
    );
  }

  return (
    <div style={{ display: 'flex', flexDirection: 'column', gap: '12px' }}>
      {tasks.map((task) => (
        <TaskCard
          key={task.id}
          task={task}
          onCompleteTask={onCompleteTask}
          onDeleteTask={onDeleteTask}
        />
      ))}
    </div>
  );
}

export default TaskList;

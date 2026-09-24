import React from 'react';
import TaskCard from './TaskCard';

function TaskBoard({ tasks, onUpdateStatus, onDeleteTask }) {
  const todoTasks = tasks.filter((t) => t.status === '未着手');
  const inProgressTasks = tasks.filter((t) => t.status === '進行中');
  const doneTasks = tasks.filter((t) => t.status === '完了');

  const columnStyle = {
    flex: 1,
    borderRadius: '8px',
    padding: '16px',
    minHeight: '200px',
  };

  return (
    <div style={{ display: 'flex', gap: '16px' }}>
      <div style={{ ...columnStyle, backgroundColor: '#f7fafc' }}>
        <div className="column-header">
          <h2 style={{ fontSize: '15px', fontWeight: '600', color: '#4a5568', marginBottom: '12px' }}>
            未着手 ({todoTasks.length})
          </h2>
        </div>
        {todoTasks.length === 0 ? (
          <p style={{ fontSize: '13px', color: '#a0aec0', textAlign: 'center', paddingTop: '16px' }}>タスクなし</p>
        ) : (
          todoTasks.map((task) => (
            <TaskCard
              key={task.id}
              task={task}
              onUpdateStatus={onUpdateStatus}
              onDeleteTask={onDeleteTask}
            />
          ))
        )}
      </div>
      <div style={{ ...columnStyle, backgroundColor: '#ebf8ff' }}>
        <div className="column-header">
          <h2 style={{ fontSize: '15px', fontWeight: '600', color: '#2b6cb0', marginBottom: '12px' }}>
            進行中 ({inProgressTasks.length})
          </h2>
        </div>
        {inProgressTasks.length === 0 ? (
          <p style={{ fontSize: '13px', color: '#a0aec0', textAlign: 'center', paddingTop: '16px' }}>タスクなし</p>
        ) : (
          inProgressTasks.map((task) => (
            <TaskCard
              key={task.id}
              task={task}
              onUpdateStatus={onUpdateStatus}
              onDeleteTask={onDeleteTask}
            />
          ))
        )}
      </div>
      <div style={{ ...columnStyle, backgroundColor: '#f0fff4' }}>
        <div className="column-header">
          <h2 style={{ fontSize: '15px', fontWeight: '600', color: '#276749', marginBottom: '12px' }}>
            完了 ({doneTasks.length})
          </h2>
        </div>
        {doneTasks.length === 0 ? (
          <p style={{ fontSize: '13px', color: '#a0aec0', textAlign: 'center', paddingTop: '16px' }}>タスクなし</p>
        ) : (
          doneTasks.map((task) => (
            <TaskCard
              key={task.id}
              task={task}
              onUpdateStatus={onUpdateStatus}
              onDeleteTask={onDeleteTask}
            />
          ))
        )}
      </div>
    </div>
  );
}

export default TaskBoard;

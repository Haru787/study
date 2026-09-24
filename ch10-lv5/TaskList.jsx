import React from 'react';
import TaskCard from './TaskCard';

function TaskList({ tasks, onUpdateStatus, onDeleteTask }) {
  if (tasks.length === 0) {
    return (
      <div style={{ textAlign: 'center', padding: '48px', color: '#a0aec0' }}>
        <p style={{ fontSize: '15px' }}>該当するタスクがありません</p>
      </div>
    );
  }

  const todoTasks = tasks.filter((t) => t.status === '未着手');
  const inProgressTasks = tasks.filter((t) => t.status === '進行中');
  const doneTasks = tasks.filter((t) => t.status === '完了');

  const renderSection = (title, sectionTasks, accentColor) => {
    if (sectionTasks.length === 0) return null;
    return (
      <div style={{ marginBottom: '24px' }}>
        <h2
          style={{
            fontSize: '14px',
            fontWeight: '700',
            color: accentColor,
            borderBottom: `2px solid ${accentColor}`,
            paddingBottom: '6px',
            marginBottom: '12px',
          }}
        >
          {title}（{sectionTasks.length}件）
        </h2>
        <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
          {sectionTasks.map((task) => (
            <TaskCard
              key={task.id}
              task={task}
              onUpdateStatus={onUpdateStatus}
              onDeleteTask={onDeleteTask}
            />
          ))}
        </div>
      </div>
    );
  };

  return (
    <div>
      {renderSection('未着手', todoTasks, '#718096')}
      {renderSection('進行中', inProgressTasks, '#2b6cb0')}
      {renderSection('完了', doneTasks, '#276749')}
    </div>
  );
}

export default TaskList;

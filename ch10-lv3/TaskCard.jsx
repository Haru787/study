import React from 'react';

const priorityConfig = {
  高: { label: '高優先', color: '#c53030', bg: '#fff5f5' },
  中: { label: '中優先', color: '#c05621', bg: '#fffaf0' },
  低: { label: '低優先', color: '#276749', bg: '#f0fff4' },
};

const statusConfig = {
  未着手: { color: '#4a5568', bg: '#e2e8f0' },
  進行中: { color: '#2b6cb0', bg: '#bee3f8' },
  完了: { color: '#276749', bg: '#c6f6d5' },
};

function TaskCard({ task }) {
  const priority = priorityConfig[task.priority] || { label: task.priority, color: '#4a5568', bg: '#e2e8f0' };
  const status = statusConfig[task.status] || { color: '#4a5568', bg: '#e2e8f0' };

  return (
    <div style={{ flex: 1 }}>
      <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginBottom: '6px', flexWrap: 'wrap' }}>
        <h3 style={{ fontSize: '15px', fontWeight: '600', color: '#2d3748', margin: 0 }}>
          {task.title}
        </h3>
        <span
          style={{
            fontSize: '11px',
            padding: '2px 8px',
            borderRadius: '9999px',
            backgroundColor: priority.bg,
            color: priority.color,
            fontWeight: '600',
          }}
        >
          {priority.label}
        </span>
        <span
          style={{
            fontSize: '11px',
            padding: '2px 8px',
            borderRadius: '9999px',
            backgroundColor: status.bg,
            color: status.color,
            fontWeight: '600',
          }}
        >
          {task.status}
        </span>
      </div>
      <p style={{ fontSize: '13px', color: '#718096', marginBottom: '8px', lineHeight: '1.5' }}>
        {task.description}
      </p>
      <div style={{ display: 'flex', gap: '16px', fontSize: '12px', color: '#a0aec0', flexWrap: 'wrap' }}>
        <span>担当: {task.assignee}</span>
        <span>期限: {task.dueDate}</span>
        <span>作成: {task.createdAt}</span>
      </div>
    </div>
  );
}

export default TaskCard;

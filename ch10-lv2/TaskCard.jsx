import React from 'react';

const priorityColors = {
  高: { bg: '#fff5f5', border: '#feb2b2', text: '#c53030' },
  中: { bg: '#fffaf0', border: '#fbd38d', text: '#c05621' },
  低: { bg: '#f0fff4', border: '#9ae6b4', text: '#276749' },
};

function TaskCard({ task, onUpdateStatus, onDeleteTask }) {
  const colors = priorityColors[task.priority] || { bg: '#f7fafc', border: '#e2e8f0', text: '#4a5568' };

  return (
    <div
      style={{
        border: `1px solid ${colors.border}`,
        borderRadius: '6px',
        padding: '12px',
        marginBottom: '10px',
        backgroundColor: colors.bg,
      }}
    >
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
        <div style={{ flex: 1 }}>
          <p style={{ fontSize: '14px', fontWeight: '600', color: '#2d3748', marginBottom: '4px' }}>
            {task.title}
          </p>
          <p style={{ fontSize: '12px', color: '#718096', marginBottom: '6px' }}>
            {task.description}
          </p>
          <div style={{ fontSize: '12px', color: '#a0aec0', display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
            <span>担当: {task.assignee}</span>
            <span>期限: {task.dueDate}</span>
            <span
              style={{
                padding: '1px 6px',
                borderRadius: '9999px',
                backgroundColor: colors.bg,
                border: `1px solid ${colors.border}`,
                color: colors.text,
                fontWeight: '600',
              }}
            >
              {task.priority}優先
            </span>
          </div>
        </div>
        <div style={{ display: 'flex', flexDirection: 'column', gap: '6px', marginLeft: '10px' }}>
          {task.status !== '進行中' && task.status !== '完了' && (
            <button
              onClick={() => onUpdateStatus(task.id, '進行中')}
              style={{ padding: '4px 10px', fontSize: '12px', backgroundColor: '#bee3f8', color: '#2b6cb0', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
            >
              着手
            </button>
          )}
          {task.status !== '完了' && (
            <button
              onClick={() => onUpdateStatus(task.id, '完了')}
              style={{ padding: '4px 10px', fontSize: '12px', backgroundColor: '#c6f6d5', color: '#276749', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
            >
              完了
            </button>
          )}
          <button
            onClick={() => onDeleteTask(task.id)}
            style={{ padding: '4px 10px', fontSize: '12px', backgroundColor: '#fff5f5', color: '#fc8181', border: '1px solid #feb2b2', borderRadius: '4px', cursor: 'pointer' }}
          >
            削除
          </button>
        </div>
      </div>
    </div>
  );
}

export default TaskCard;

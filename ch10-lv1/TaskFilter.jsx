import React from 'react';

const STATUS_OPTIONS = ['すべて', '未着手', '進行中', '完了'];

function TaskFilter({ filterStatus, onFilterChange }) {
  return (
    <div style={{ display: 'flex', gap: '8px', marginBottom: '16px', flexWrap: 'wrap' }}>
      <span style={{ fontSize: '14px', color: '#4a5568', alignSelf: 'center', marginRight: '4px' }}>
        フィルター:
      </span>
      {STATUS_OPTIONS.map((status) => (
        <button
          key={status}
          onClick={() => onFilterChange(status)}
          style={{
            padding: '6px 16px',
            fontSize: '13px',
            border: '1px solid',
            borderColor: filterStatus === status ? '#4299e1' : '#e2e8f0',
            borderRadius: '9999px',
            backgroundColor: filterStatus === status ? '#ebf8ff' : '#ffffff',
            color: filterStatus === status ? '#2b6cb0' : '#718096',
            cursor: 'pointer',
            fontWeight: filterStatus === status ? '600' : '400',
          }}
        >
          {status}
        </button>
      ))}
    </div>
  );
}

export default TaskFilter;

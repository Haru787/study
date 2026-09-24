import React from 'react';

const STATUS_OPTIONS = ['すべて', '未着手', '進行中', '完了'];

function FilterPanel({ filterStatus, onFilterChange }) {
  return (
    <div style={{ display: 'flex', gap: '6px', flexWrap: 'wrap', alignItems: 'center' }}>
      <span style={{ fontSize: '13px', color: '#718096' }}>ステータス:</span>
      {STATUS_OPTIONS.map((status) => (
        <button
          key={status}
          onClick={() => onFilterChange(status)}
          style={{
            padding: '6px 14px',
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

export default FilterPanel;

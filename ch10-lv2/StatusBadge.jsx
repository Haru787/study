import React from 'react';

const badgeStyles = {
  未着手: { backgroundColor: '#e2e8f0', color: '#4a5568' },
  進行中: { backgroundColor: '#bee3f8', color: '#2b6cb0' },
  完了: { backgroundColor: '#c6f6d5', color: '#276749' },
};

function StatusBadge({ status, count }) {
  const style = badgeStyles[status] || { backgroundColor: '#e2e8f0', color: '#4a5568' };

  return (
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        gap: '6px',
        padding: '6px 14px',
        borderRadius: '9999px',
        fontSize: '13px',
        fontWeight: '500',
        ...style,
      }}
    >
      <span>{status}</span>
      <span
        style={{
          display: 'inline-flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: '20px',
          height: '20px',
          borderRadius: '9999px',
          backgroundColor: 'rgba(0,0,0,0.1)',
          fontSize: '12px',
          fontWeight: '700',
        }}
      >
        {count}
      </span>
    </div>
  );
}

export default StatusBadge;

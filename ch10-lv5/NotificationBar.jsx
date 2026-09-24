import React, { useState, useEffect } from 'react';

const typeConfig = {
  success: { backgroundColor: '#f0fff4', borderColor: '#9ae6b4', color: '#276749' },
  info: { backgroundColor: '#ebf8ff', borderColor: '#90cdf4', color: '#2b6cb0' },
  warning: { backgroundColor: '#fffbeb', borderColor: '#fbd38d', color: '#c05621' },
  error: { backgroundColor: '#fff5f5', borderColor: '#feb2b2', color: '#c53030' },
};

function NotificationBar({ message, type, onClose }) {
  const [visible, setVisible] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setVisible(false);
      onClose();
    }, 3000);

    return () => clearTimeout(timer);
  }, [message]);

  if (!visible) return null;

  const style = typeConfig[type] || typeConfig.info;

  return (
    <div
      style={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: '12px 16px',
        marginBottom: '16px',
        borderRadius: '6px',
        border: `1px solid ${style.borderColor}`,
        backgroundColor: style.backgroundColor,
        color: style.color,
        fontSize: '14px',
      }}
    >
      <span>{message}</span>
      <button
        onClick={() => { setVisible(false); onClose(); }}
        style={{
          marginLeft: '12px',
          background: 'none',
          border: 'none',
          cursor: 'pointer',
          fontSize: '16px',
          color: style.color,
          lineHeight: 1,
        }}
      >
        ✕
      </button>
    </div>
  );
}

export default NotificationBar;

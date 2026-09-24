import React from 'react';

function SearchBar({ keyword, onKeywordChange }) {
  return (
    <div style={{ flex: 1, minWidth: '200px' }}>
      <input
        type="text"
        value={keyword}
        onChange={(e) => onKeywordChange(e.target.value)}
        placeholder="タイトル・担当者・説明で検索..."
        style={{
          width: '100%',
          padding: '8px 12px',
          border: '1px solid #e2e8f0',
          borderRadius: '6px',
          fontSize: '14px',
          boxSizing: 'border-box',
          outline: 'none',
        }}
      />
    </div>
  );
}

export default SearchBar;

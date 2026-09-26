'use client';

import { useState } from 'react';

type Props = {
  productId: number;
  isActive: boolean;
};

export default function ProductActions({ productId, isActive }: Props) {
  const [loading, setLoading] = useState(false);

  const handleEdit = () => {
    window.location.href = `/products/${productId}/edit`;
  };

  const handleToggleStatus = async () => {
    setLoading(true);
    try {
      const res = await fetch(`/api/products/${productId}/status`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ isActive: !isActive }),
      });
      if (!res.ok) throw new Error('ステータスの更新に失敗しました');
      window.location.reload();
    } catch (err) {
      alert(err instanceof Error ? err.message : 'エラーが発生しました');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex gap-3 mt-6">
      <button
        onClick={handleEdit}
        className="px-5 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700"
      >
        編集する
      </button>
      <button
        onClick={handleToggleStatus}
        disabled={loading}
        className={`px-5 py-2 rounded-md text-white disabled:opacity-50 ${
          isActive ? 'bg-red-500 hover:bg-red-600' : 'bg-green-500 hover:bg-green-600'
        }`}
      >
        {loading ? '処理中...' : isActive ? '販売停止にする' : '販売再開する'}
      </button>
    </div>
  );
}

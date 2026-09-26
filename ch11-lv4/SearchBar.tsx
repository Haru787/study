'use client';

import { useRouter, useSearchParams } from 'next/navigation';
import { useState } from 'react';

const CATEGORIES = ['電化製品', '衣類', '食品', '家具', 'その他'];

export default function SearchBar() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const [keyword, setKeyword] = useState(searchParams.get('keyword') ?? '');
  const [category, setCategory] = useState(searchParams.get('category') ?? '');

  const handleSearch = () => {
    const params = new URLSearchParams();
    if (keyword) params.set('keyword', keyword);
    if (category) params.set('category', category);
    router.push(`/products?${params}`);
  };

  return (
    <div className="flex flex-col sm:flex-row gap-3 mb-6">
      <input
        type="text"
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
        onKeyDown={(e) => e.key === 'Enter' && handleSearch()}
        placeholder="商品名で検索..."
        className="border rounded-md px-3 py-2 flex-1 focus:outline-none focus:ring-2 focus:ring-indigo-400"
      />
      <select
        value={category}
        onChange={(e) => setCategory(e.target.value)}
        className="border rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"
      >
        <option value="">すべてのカテゴリ</option>
        {CATEGORIES.map((cat) => (
          <option key={cat} value={cat}>{cat}</option>
        ))}
      </select>
      <button
        onClick={handleSearch}
        className="px-5 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700"
      >
        検索
      </button>
    </div>
  );
}

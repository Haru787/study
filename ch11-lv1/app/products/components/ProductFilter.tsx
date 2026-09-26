'use client';

import { useState } from 'react';
import type { ProductCategory } from '@/types/product';

const CATEGORIES: ProductCategory[] = ['電化製品', '衣類', '食品', '家具', 'その他'];

type Props = {
  onCategoryChange?: (category: string) => void;
  onSearchChange?: (keyword: string) => void;
};

export default function ProductFilter({ onCategoryChange, onSearchChange }: Props) {
  const [selectedCategory, setSelectedCategory] = useState<string>('');
  const [keyword, setKeyword] = useState<string>('');

  const handleCategoryChange = (category: string) => {
    setSelectedCategory(category);
    onCategoryChange?.(category);
  };

  const handleKeywordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setKeyword(e.target.value);
    onSearchChange?.(e.target.value);
  };

  return (
    <div className="flex flex-col sm:flex-row gap-4 mb-6">
      <input
        type="text"
        value={keyword}
        onChange={handleKeywordChange}
        placeholder="商品名で検索..."
        className="border rounded-md px-3 py-2 flex-1 focus:outline-none focus:ring-2 focus:ring-indigo-400"
      />
      <select
        value={selectedCategory}
        onChange={(e) => handleCategoryChange(e.target.value)}
        className="border rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-indigo-400"
      >
        <option value="">すべてのカテゴリ</option>
        {CATEGORIES.map((cat) => (
          <option key={cat} value={cat}>
            {cat}
          </option>
        ))}
      </select>
    </div>
  );
}

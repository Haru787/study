'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import type { ProductFormData } from '@/types/product';
import ProductForm from '../components/ProductForm';
import { createProductAction } from '@/app/actions/productActions';

const CATEGORIES = ['電化製品', '衣類', '食品', '家具', 'その他'];

export default function ProductCreatePage() {
  const router = useRouter();
  const [error, setError] = useState<string | null>(null);

  const handleCreate = async (data: ProductFormData) => {
    try {
      await createProductAction(data);
      router.push('/products');
    } catch (err) {
      setError(err instanceof Error ? err.message : '登録に失敗しました');
    }
  };

  return (
    <main className="max-w-2xl mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">商品登録</h1>
      {error && (
        <div className="bg-red-50 border border-red-300 text-red-700 rounded-md px-4 py-3 mb-4">
          {error}
        </div>
      )}
      <ProductForm
        categories={CATEGORIES}
        onSubmit={handleCreate}
        submitLabel="登録する"
      />
    </main>
  );
}

import type { Metadata } from 'next';
import { createProduct } from '@/lib/api';
import ProductForm from '../components/ProductForm';
import type { ProductFormData } from '@/types/product';

export const metadata: Metadata = {
  title: '商品登録 | ECサイト管理画面',
};

export default async function ProductCreatePage() {
  const handleCreate = async (data: ProductFormData) => {
    'use server';
    await createProduct(data);
  };

  return (
    <main className="max-w-2xl mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold text-gray-800 mb-6">商品登録</h1>
      <ProductForm onSubmit={handleCreate} submitLabel="登録する" />
    </main>
  );
}

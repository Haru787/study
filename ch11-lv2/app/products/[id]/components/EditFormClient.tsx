'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { updateProduct } from '@/lib/api';
import ProductForm from '../../components/ProductForm';
import type { Product, ProductFormData } from '@/types/product';

type Props = {
  product: Product;
};

export default function EditFormClient({ product }: Props) {
  const router = useRouter();
  const [error, setError] = useState<string | null>(null);

  const handleUpdate = async (data: ProductFormData) => {
    try {
      await updateProduct(product.id, data);
      router.push('/products');
    } catch (err) {
      setError(err instanceof Error ? err.message : '更新に失敗しました');
    }
  };

  return (
    <>
      {error && (
        <div className="bg-red-50 border border-red-300 text-red-700 rounded-md px-4 py-3 mb-4">
          {error}
        </div>
      )}
      <ProductForm
        initialData={product}
        onSubmit={handleUpdate}
        submitLabel="更新する"
      />
    </>
  );
}

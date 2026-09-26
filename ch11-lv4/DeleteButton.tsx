'use client';

import { useRouter } from 'next/navigation';
import { deleteProduct } from '@/lib/productApi';

type Props = {
  productId: number;
};

export default function DeleteButton({ productId }: Props) {
  const router = useRouter();

  const handleDelete = async () => {
    if (!confirm('この商品を削除してもよろしいですか？')) return;
    try {
      await deleteProduct(productId);
      router.push('/products');
    } catch (err) {
      alert(err instanceof Error ? err.message : '削除に失敗しました');
    }
  };

  return (
    <button
      onClick={handleDelete}
      className="px-5 py-2 bg-red-600 text-white rounded-md hover:bg-red-700"
    >
      削除する
    </button>
  );
}

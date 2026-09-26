import type { Product } from '@/types/product';

type Props = {
  product: Product | null;
};

export default function PriceDisplay({ product }: Props) {
  const formatted = product?.price?.toFixed(0);

  return (
    <div className="mt-2">
      <span className="text-4xl font-extrabold text-indigo-600">
        {formatted !== undefined ? `¥${Number(formatted).toLocaleString()}` : '-'}
      </span>
      <span className="text-sm text-gray-500 ml-2">（税込）</span>
    </div>
  );
}

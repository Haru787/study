import type { Product } from '@/types/product';

type Props = {
  product: Product;
};

export default function ProductCard({ product }: Props) {
  return (
    <div className="border rounded-lg p-4 shadow-sm hover:shadow-md transition-shadow">
      <img
        src={product.imageUrl}
        alt={product.name}
        className="w-full h-48 object-cover rounded-md mb-3"
      />
      <h3 className="text-lg font-semibold text-gray-800 mb-1">{product.name}</h3>
      <p className="text-sm text-gray-500 mb-2">{product.category}</p>
      <p className="text-xl font-bold text-indigo-600">
        ¥{product.price.toLocaleString()}
      </p>
      <p className="text-sm text-gray-500 mt-1">在庫: {product.stock}点</p>
      <span
        className={`inline-block mt-2 text-xs px-2 py-1 rounded-full ${
          product.isActive
            ? 'bg-green-100 text-green-700'
            : 'bg-gray-100 text-gray-500'
        }`}
      >
        {product.isActive ? '販売中' : '販売停止'}
      </span>
    </div>
  );
}

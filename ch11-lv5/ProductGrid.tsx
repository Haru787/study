import type { Product } from '@/types/product';
import Link from 'next/link';

type Props = {
  products: Product[];
};

export default function ProductGrid({ products }: Props) {
  if (products.length === 0) {
    return (
      <div className="col-span-full text-center py-16 text-gray-400">
        <p className="text-lg">商品が見つかりませんでした</p>
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
      {products.map((product) => (
        <Link
          key={product.id}
          href={`/products/${product.id}`}
          className="block border rounded-xl p-4 hover:shadow-lg transition-shadow bg-white"
        >
          <img
            src={product.imageUrl}
            alt={product.name}
            className="w-full h-44 object-cover rounded-lg mb-3"
          />
          <h3 className="font-semibold text-gray-800 truncate">{product.name}</h3>
          <p className="text-sm text-gray-500 mb-1">{product.category}</p>
          <p className="text-lg font-bold text-indigo-600">
            ¥{product.price.toLocaleString()}
          </p>
          <div className="flex items-center justify-between mt-2">
            <span className="text-xs text-gray-400">在庫: {product.stock}点</span>
            <span
              className={`text-xs px-2 py-0.5 rounded-full ${
                product.isActive
                  ? 'bg-green-100 text-green-700'
                  : 'bg-gray-100 text-gray-400'
              }`}
            >
              {product.isActive ? '販売中' : '停止中'}
            </span>
          </div>
        </Link>
      ))}
    </div>
  );
}

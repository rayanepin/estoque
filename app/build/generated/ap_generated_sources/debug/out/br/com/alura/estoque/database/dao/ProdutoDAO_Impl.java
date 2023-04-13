package br.com.alura.estoque.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.com.alura.estoque.database.converter.BigDecimalConverter;
import br.com.alura.estoque.model.Produto;
import java.lang.Class;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProdutoDAO_Impl implements ProdutoDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Produto> __insertionAdapterOfProduto;

  private final BigDecimalConverter __bigDecimalConverter = new BigDecimalConverter();

  private final EntityDeletionOrUpdateAdapter<Produto> __deletionAdapterOfProduto;

  private final EntityDeletionOrUpdateAdapter<Produto> __updateAdapterOfProduto;

  public ProdutoDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProduto = new EntityInsertionAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Produto` (`id`,`nome`,`preco`,`quantidade`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        final Double _tmp = __bigDecimalConverter.paraDouble(value.getPreco());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, _tmp);
        }
        stmt.bindLong(4, value.getQuantidade());
      }
    };
    this.__deletionAdapterOfProduto = new EntityDeletionOrUpdateAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Produto` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfProduto = new EntityDeletionOrUpdateAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Produto` SET `id` = ?,`nome` = ?,`preco` = ?,`quantidade` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        final Double _tmp = __bigDecimalConverter.paraDouble(value.getPreco());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, _tmp);
        }
        stmt.bindLong(4, value.getQuantidade());
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public long salva(final Produto produto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfProduto.insertAndReturnId(produto);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void remove(final Produto produto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfProduto.handle(produto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualiza(final Produto produto) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfProduto.handle(produto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Produto> buscaTodos() {
    final String _sql = "SELECT * FROM Produto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfPreco = CursorUtil.getColumnIndexOrThrow(_cursor, "preco");
      final int _cursorIndexOfQuantidade = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidade");
      final List<Produto> _result = new ArrayList<Produto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Produto _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpNome;
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _tmpNome = null;
        } else {
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
        }
        final BigDecimal _tmpPreco;
        final Double _tmp;
        if (_cursor.isNull(_cursorIndexOfPreco)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getDouble(_cursorIndexOfPreco);
        }
        _tmpPreco = __bigDecimalConverter.paraBigDecimal(_tmp);
        final int _tmpQuantidade;
        _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
        _item = new Produto(_tmpId,_tmpNome,_tmpPreco,_tmpQuantidade);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Produto buscaProduto(final long id) {
    final String _sql = "SELECT * FROM Produto WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfPreco = CursorUtil.getColumnIndexOrThrow(_cursor, "preco");
      final int _cursorIndexOfQuantidade = CursorUtil.getColumnIndexOrThrow(_cursor, "quantidade");
      final Produto _result;
      if(_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpNome;
        if (_cursor.isNull(_cursorIndexOfNome)) {
          _tmpNome = null;
        } else {
          _tmpNome = _cursor.getString(_cursorIndexOfNome);
        }
        final BigDecimal _tmpPreco;
        final Double _tmp;
        if (_cursor.isNull(_cursorIndexOfPreco)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getDouble(_cursorIndexOfPreco);
        }
        _tmpPreco = __bigDecimalConverter.paraBigDecimal(_tmp);
        final int _tmpQuantidade;
        _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
        _result = new Produto(_tmpId,_tmpNome,_tmpPreco,_tmpQuantidade);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

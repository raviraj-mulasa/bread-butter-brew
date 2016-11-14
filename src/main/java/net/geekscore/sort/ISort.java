package net.geekscore.sort;

import java.util.Collection;

/**
 * Created by ravirajmulasa on 8/26/16.
 */
public interface ISort<T> {

    Collection<T> sort(T[] elements2sort, boolean asc);
}

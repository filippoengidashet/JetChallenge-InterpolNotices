package com.filippoengidashet.challenge4.lloyds.ui.screens.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.filippoengidashet.challenge4.lloyds.domain.model.InterpolNotice
import com.filippoengidashet.challenge4.lloyds.domain.model.RedNoticesParams
import com.filippoengidashet.challenge4.lloyds.domain.usecase.GetInterpolNoticesUseCase

class InterpolNoticesPagingSource(
    private val getNoticesUseCase: GetInterpolNoticesUseCase,
    private val keyword: String? = null
) : PagingSource<Int, InterpolNotice>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InterpolNotice> {
        val pageIndex = params.key ?: 1
        return try {

            val requestParams = RedNoticesParams(
                page = pageIndex,
                offset = params.loadSize,
                keyword = keyword
            )
            val response = getNoticesUseCase.invoke(requestParams)
            val prevKey = if (pageIndex == 1) null else pageIndex
            val nextKey =
                if ((response.page * response.items.size) < response.total) pageIndex + 1 else null
            LoadResult.Page(
                data = response.items, prevKey = prevKey, nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, InterpolNotice>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    companion object {

        const val PAGE_OFFSET = 50
        const val PREFETCH_DISTANCE = 10
    }
}

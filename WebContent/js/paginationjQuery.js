
/*
DataScrubber For javascript Pagination

 */

jQuery.DataScrubber = function (dataA, pages2ExtractA, recordsPerPageA) {
	var data = dataA;
	var defaultScrubCount = 1;
	var currentPageCount = 0;
	var totalRecords = data.length;
	var recordsPerPage = totalRecords <= recordsPerPageA ? totalRecords : recordsPerPageA;
	var totalPages = Math.ceil(totalRecords / recordsPerPageA);
	var pages2Extract = pages2ExtractA >= totalPages ? totalPages : pages2ExtractA;
	var recStartIdx = 0;
	var recEndIdx = 0;
	var pageStartIdx = (currentPageCount + 1);
	var pageEndIdx = pages2Extract;
	this.scrubRight = function () {
		var scrubData = {};
		currentPageCount++;
		if (currentPageCount > totalPages) {
			currentPageCount = totalPages;
		}
		recEndIdx = currentPageCount * recordsPerPage;
		recStartIdx = recEndIdx - recordsPerPage;
		if (currentPageCount > pageEndIdx) {
			pageEndIdx += pages2Extract;
			if (pageEndIdx > totalPages) {
				pageEndIdx = totalPages;
			}
			pageStartIdx = pageEndIdx - pages2Extract + 1;
		}
		scrubData.currentPageIdx = currentPageCount - pageStartIdx;
		scrubData.paginationStart = pageStartIdx;
		scrubData.paginationEnd = pageEndIdx;
		scrubData.records = data.slice(recStartIdx, recEndIdx);
		return scrubData;
	};
	this.scrubLeft = function () {
		var scrubData = {};
		currentPageCount--;
		if (currentPageCount < defaultScrubCount) {
			currentPageCount = defaultScrubCount;
		}
		recEndIdx = currentPageCount * recordsPerPage;
		recStartIdx = recEndIdx - recordsPerPage;
		if (currentPageCount < pageStartIdx) {
			pageStartIdx -= pages2Extract;
			if (pageStartIdx < 1) {
				pageStartIdx = 1;
			}
			pageEndIdx = pageStartIdx + pages2Extract - 1;
		}
		scrubData.currentPageIdx = currentPageCount - pageStartIdx;
		scrubData.paginationStart = pageStartIdx;
		scrubData.paginationEnd = pageEndIdx;
		scrubData.records = data.slice(recStartIdx, recEndIdx);
		return scrubData;
	};
	this.doScrub = function (cpCount) {
		var sData = {};
		currentPageCount = cpCount;
		recEndIdx = recordsPerPage * currentPageCount;
		recEndIdx = recEndIdx < recordsPerPage ? recordsPerPage : recEndIdx;
		recStartIdx = recEndIdx - recordsPerPage;
		recStartIdx = recStartIdx < 0 ? 0 : recStartIdx;
		sData.currentPageIdx = currentPageCount - pageStartIdx;
		sData.paginationStart = pageStartIdx;
		sData.paginationEnd = pageEndIdx;
		sData.records = data.slice(recStartIdx, recEndIdx);
		return sData;
	}
	this.init = function () {
		return this.scrubRight();
	};
};
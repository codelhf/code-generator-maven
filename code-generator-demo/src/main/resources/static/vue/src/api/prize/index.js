import request from '@/utils/request'

export function fetchPrizeList(query) {
  return request({
    url: '/mm/prize',
    method: 'GET',
    params: query
  })
}

export function fetchPrize(id) {
  return request({
    url: '/mm/prize/' + id,
    method: 'GET'
})
}

export function createPrize(formData) {
  return request({
    url: '/mm/prize',
    method: 'POST',
    data: formData
  })
}

export function updatePrize(formData) {
  return request({
    url: '/mm/prize/' + formData.id,
    method: 'PUT',
    data: formData
  })
}

export function deletePrize(ids) {
  return request({
    url: '/mm/prize/' + ids,
    method: 'DELETE'
  })
}

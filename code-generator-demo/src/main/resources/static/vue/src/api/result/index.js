import request from '@/utils/request'

export function fetchResultList(query) {
  return request({
    url: '/mm/result',
    method: 'GET',
    params: query
  })
}

export function fetchResult(id) {
  return request({
    url: '/mm/result/' + id,
    method: 'GET'
})
}

export function createResult(formData) {
  return request({
    url: '/mm/result',
    method: 'POST',
    data: formData
  })
}

export function updateResult(formData) {
  return request({
    url: '/mm/result/' + formData.id,
    method: 'PUT',
    data: formData
  })
}

export function deleteResult(ids) {
  return request({
    url: '/mm/result/' + ids,
    method: 'DELETE'
  })
}
